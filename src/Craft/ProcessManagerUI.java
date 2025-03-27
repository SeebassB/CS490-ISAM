package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.Analysis.AnalysisProcess;
import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.CraftProcesses.WakeUpProcess;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ProcessManagerUI extends JFrame {
    private JTextPane queueTextPane;
    private JTextArea debugLog;
    private ProcessQueue processQueue;
    private craftThread craft;
    private Thread craftThreadInstance;
    private JButton startPauseButton;
    private JButton removeProcessButton;
    private JButton clearAllButton;

    private boolean isRunning = true;
    private JComboBox<Integer> priorityDropdown;
    private JSlider executionTimeSlider;
    private Map<Integer, Color> priorityColors;
    private JPanel progressPanel;
    private Map<ProcessControlBlock, JProgressBar> progressBars;
    private Map<ProcessControlBlock, JLabel> progressLabels;
    private JComboBox<String> processDropdown;
    private SimpleDateFormat timeFormatter;

    // For highlighting the active process and showing time left
    private ProcessControlBlock currentProcess = null;
    private Map<ProcessControlBlock, Integer> timeLeftMap = new HashMap<>();

    public ProcessManagerUI() {
        setTitle("Process Manager");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1) Initialize the Queue
        processQueue = craftThread.getMainQueue();
        progressBars = new HashMap<>();
        progressLabels = new HashMap<>();
        timeFormatter = new SimpleDateFormat("HH:mm:ss");

        // 2) Priority Colors
        priorityColors = new HashMap<>();
        priorityColors.put(0, Color.RED);
        priorityColors.put(1, Color.ORANGE);
        priorityColors.put(2, Color.YELLOW);
        priorityColors.put(3, Color.GREEN);
        priorityColors.put(4, Color.BLUE);
        priorityColors.put(5, Color.GRAY);

        // 3) Setup the main UI sections
        // -- North: Controls panel
        JPanel controlPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        add(controlPanel, BorderLayout.NORTH);

        // **Queue Text**: placed in the West region
        queueTextPane = new JTextPane();
        queueTextPane.setEditable(false);
        JScrollPane queueScrollPane = new JScrollPane(queueTextPane);
        queueScrollPane.setPreferredSize(new Dimension(400, 600));
        add(queueScrollPane, BorderLayout.WEST);

        // **Debug Log**: placed in the Center
        debugLog = new JTextArea();
        debugLog.setEditable(false);
        debugLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane debugScrollPane = new JScrollPane(debugLog);
        add(debugScrollPane, BorderLayout.CENTER);

        // **Progress Panel**: placed in the East region
        progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        JScrollPane progressScrollPane = new JScrollPane(progressPanel);
        progressScrollPane.setPreferredSize(new Dimension(450, 600));
        add(progressScrollPane, BorderLayout.EAST);

        // **Start/Pause** button: placed in the South
        startPauseButton = new JButton("Pause Execution");
        startPauseButton.addActionListener(e -> toggleExecution());
        add(startPauseButton, BorderLayout.SOUTH);

        // 4) Setup the sub-controls in the North Panel
        JButton addAnalysisProcess = new JButton("Add Analysis Process");
        JButton addArmProcess = new JButton("Add Arm Process");
        removeProcessButton = new JButton("Remove Selected Process");
        clearAllButton = new JButton("Clear All Processes");

        priorityDropdown = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5});
        executionTimeSlider = new JSlider(1, 10, 5);
        executionTimeSlider.setMajorTickSpacing(1);
        executionTimeSlider.setPaintTicks(true);
        executionTimeSlider.setPaintLabels(true);

        processDropdown = new JComboBox<>();
        processDropdown.setPreferredSize(new Dimension(200, 30));

        controlPanel.add(new JLabel("Priority:"));
        controlPanel.add(priorityDropdown);
        controlPanel.add(new JLabel("Execution Time (sec):"));
        controlPanel.add(executionTimeSlider);
        controlPanel.add(addAnalysisProcess);
        controlPanel.add(addArmProcess);
        controlPanel.add(new JLabel("Select Process to Remove:"));
        controlPanel.add(processDropdown);
        controlPanel.add(removeProcessButton);
        controlPanel.add(clearAllButton);

        // 5) Button Listeners
        addAnalysisProcess.addActionListener(e -> addProcessToQueue(new AnalysisProcess(), "Analysis Process"));
        addArmProcess.addActionListener(e -> addProcessToQueue(new ArmProcess(), "Arm Process"));
        removeProcessButton.addActionListener(e -> removeSelectedProcess());
        clearAllButton.addActionListener(e -> clearAllProcesses());

        // 6) Start the background thread
        craft = new craftThread(processQueue, this);
        craftThreadInstance = new Thread(craft);
        craftThreadInstance.start();

       // addProcessToQueue(new WakeUpProcess(), "Wakeup Process");
    }

    // ------------------------------------------------------
    // Process UI Functions
    // ------------------------------------------------------

    private void addProcessToQueue(ProcessBase process, String name) {
        int priority = (int) priorityDropdown.getSelectedItem();
        int executionTime = executionTimeSlider.getValue();

        ProcessControlBlock pcb = new ProcessControlBlock(name, priority, executionTime, process);
        processQueue.addProcess(pcb);

        // Create and add a new progress bar & label for this process
        JLabel processLabel = new JLabel("Process ID: " + pcb.getProcessID() + " - " + name + " (Priority: " + priority + ")");
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        progressBars.put(pcb, progressBar);
        progressLabels.put(pcb, processLabel);

        progressPanel.add(processLabel);
        progressPanel.add(progressBar);
        progressPanel.revalidate();
        progressPanel.repaint();

        processDropdown.addItem("ID: " + pcb.getProcessID() + " - " + name);

        updateQueueDisplay();
        logMessage("[" + timeFormatter.format(new Date()) + "] Added Process ID: " + pcb.getProcessID() + " - " + name + " (Priority: " + priority + ")");
    }

    private void clearAllProcesses() {

        addProcessToQueue(new WakeUpProcess(), "Wake Up Process");
        /*
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove all processes?", "Confirm Clear All", JOptionPane.YES_NO_OPTION);
        if (response != JOptionPane.YES_OPTION) {
            return;
        }

        // Clear the queue
        processQueue = new ProcessQueue();

        // Clear UI components
        processDropdown.removeAllItems();
        progressPanel.removeAll();
        progressBars.clear();
        progressLabels.clear();
        updateQueueDisplay();
        debugLog.setText(""); // Clear logs
        progressPanel.revalidate();
        progressPanel.repaint();

        logMessage("[" + timeFormatter.format(new Date()) + "] All processes cleared.");

         */
    }

    private void removeSelectedProcess() {
        String selectedProcess = (String) processDropdown.getSelectedItem();
        if (selectedProcess == null) return;

        int processID = Integer.parseInt(selectedProcess.split(" ")[1]);

        ProcessControlBlock temp = processQueue.getHead();
        ProcessControlBlock previous = null;

        while (temp != null) {
            if (temp.getProcessID() == processID) {
                // Update the queue
                if (previous == null) {
                    processQueue.setHead(temp.getNext());
                } else {
                    previous.setNext(temp.getNext());
                }

                // Remove UI elements
                JProgressBar progressBar = progressBars.remove(temp);
                JLabel processLabel = progressLabels.remove(temp);
                if (progressBar != null) progressPanel.remove(progressBar);
                if (processLabel != null) progressPanel.remove(processLabel);

                progressPanel.revalidate();
                progressPanel.repaint();
                processDropdown.removeItem(selectedProcess);
                updateQueueDisplay();
                logMessage("[" + timeFormatter.format(new Date()) + "] Removed Process ID: " + processID + " - " + temp.getName());
                return;
            }
            previous = temp;
            temp = temp.getNext();
        }
    }

    public void logMessage(String message) {
        debugLog.append(message + "\n");
    }

    // ------------------------------------------------------
    // Time Left & Current Process
    // ------------------------------------------------------

    public void setCurrentProcess(ProcessControlBlock pcb) {
        this.currentProcess = pcb;
    }

    public void updateTimeLeft(ProcessControlBlock pcb, int secondsLeft) {
        timeLeftMap.put(pcb, secondsLeft);
        updateQueueDisplay();
    }

    // ------------------------------------------------------
    // Queue Display
    // ------------------------------------------------------

    public void updateQueueDisplay() {
        // If we're off the EDT, bounce the call
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::updateQueueDisplay);
            return;
        }

        List<ProcessControlBlock> sortedQueue = new ArrayList<>();
        ProcessControlBlock temp = processQueue.getHead();

        while (temp != null) {
            sortedQueue.add(temp);
            if (!progressBars.containsKey(temp)) {
                JLabel label = new JLabel("Process ID: " + temp.getProcessID() + " - " + temp.getName());
                JProgressBar pb = new JProgressBar(0,100);
                pb.setStringPainted(true);
                progressBars.put(temp, pb);
                progressLabels.put(temp, label);
                progressPanel.add(label);
                progressPanel.add(pb);
                progressPanel.revalidate();
                progressPanel.repaint();
            }
            temp = temp.getNext();
        }

        // Sort ascending by priority
        sortedQueue.sort(Comparator.comparingInt(ProcessControlBlock::getPriority));

        try {
            StyledDocument doc = queueTextPane.getStyledDocument();
            doc.remove(0, doc.getLength());

            SimpleAttributeSet attr = new SimpleAttributeSet();

            for (ProcessControlBlock pcb : sortedQueue) {
                if (pcb == currentProcess) {
                    StyleConstants.setForeground(attr, Color.MAGENTA);
                } else {
                    int priority = pcb.getPriority();
                    StyleConstants.setForeground(attr, priorityColors.getOrDefault(priority, Color.BLACK));
                }

                int priority = pcb.getPriority();
                int secondsLeft = timeLeftMap.getOrDefault(pcb, 0);

                // If this is the current process, append "time left: Xs"
                String extraTime = "";
                if (pcb == currentProcess && secondsLeft > 0) {
                    extraTime = " | time left: " + secondsLeft + "s";
                }

                String text = "Process ID: " + pcb.getProcessID() +
                        " | " + pcb.getName() +
                        " | Priority: " + priority +
                        " | Execution Time: " + pcb.getExecutionTime() + "s" +
                        extraTime + "\n";

                doc.insertString(doc.getLength(), text, attr);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------
    // Pause/Resume
    // ------------------------------------------------------

    private void toggleExecution() {
        isRunning = !isRunning;
        craft.setRunning(isRunning);
        startPauseButton.setText(isRunning ? "Pause Execution" : "Resume Execution");
        logMessage(isRunning ? "Execution Resumed" : "Execution Paused");
    }

    public void updateProgress(ProcessControlBlock pcb, int value) {
        JProgressBar progressBar = progressBars.get(pcb);
        if (progressBar != null) {
            progressBar.setValue(value);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    // ------------------------------------------------------
    // Main for Testing
    // ------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProcessManagerUI().setVisible(true));
    }
}
