package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.Analysis.AnalysisProcess;
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
    private JTextArea debugLog;
    private JTextPane queueTextPane;
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

    public ProcessManagerUI() {
        setTitle("Process Manager");
        setSize(1300, 800); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize queue and UI elements
        processQueue = new ProcessQueue();
        progressBars = new HashMap<>();
        progressLabels = new HashMap<>();
        timeFormatter = new SimpleDateFormat("HH:mm:ss");

        // Text pane for displaying queue with styled color
        queueTextPane = new JTextPane();
        queueTextPane.setEditable(false);

        // ScrollPane for better scrolling
        JScrollPane queueScrollPane = new JScrollPane(queueTextPane);
        queueScrollPane.setPreferredSize(new Dimension(400, 600));

        debugLog = new JTextArea();
        debugLog.setEditable(false);
        debugLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Initialize priority colors
        priorityColors = new HashMap<>();
        priorityColors.put(0, Color.RED);
        priorityColors.put(1, Color.ORANGE);
        priorityColors.put(2, Color.YELLOW);
        priorityColors.put(3, Color.GREEN);
        priorityColors.put(4, Color.BLUE);
        priorityColors.put(5, Color.GRAY);

        // Panel for Buttons and Inputs
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(6, 2, 10, 10));

        JButton addAnalysisProcess = new JButton("Add Analysis Process");
        JButton addArmProcess = new JButton("Add Arm Process");
        startPauseButton = new JButton("Pause Execution");
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
        controlPanel.add(clearAllButton); // Added clear all button

        // Panel for individual process progress bars
        progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        JScrollPane progressScrollPane = new JScrollPane(progressPanel);
        progressScrollPane.setPreferredSize(new Dimension(450, 600));

        // Button Listeners
        addAnalysisProcess.addActionListener(e -> addProcessToQueue(new AnalysisProcess(), "Analysis Process"));
        addArmProcess.addActionListener(e -> addProcessToQueue(new ArmProcess(), "Arm Process"));
        startPauseButton.addActionListener(e -> toggleExecution());
        removeProcessButton.addActionListener(e -> removeSelectedProcess());
        clearAllButton.addActionListener(e -> clearAllProcesses()); // New button listener

        // UI Layout
        add(controlPanel, BorderLayout.NORTH);
        add(queueScrollPane, BorderLayout.WEST);
        add(new JScrollPane(debugLog), BorderLayout.CENTER);
        add(progressScrollPane, BorderLayout.EAST);
        add(startPauseButton, BorderLayout.SOUTH);

        // Start the background thread for execution
        craft = new craftThread(processQueue, this);
        craftThreadInstance = new Thread(craft);
        craftThreadInstance.start();
    }

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
        // Confirm before clearing
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
    }

    private void removeSelectedProcess() {
        String selectedProcess = (String) processDropdown.getSelectedItem();
        if (selectedProcess == null) return;

        int processID = Integer.parseInt(selectedProcess.split(" ")[1]);

        ProcessControlBlock temp = processQueue.getHead();
        ProcessControlBlock previous = null;

        while (temp != null) {
            if (temp.getProcessID() == processID) {
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

    public void updateQueueDisplay() {
        List<ProcessControlBlock> sortedQueue = new ArrayList<>();
        ProcessControlBlock temp = processQueue.getHead();

        while (temp != null) {
            sortedQueue.add(temp);
            temp = temp.getNext();
        }

        sortedQueue.sort(Comparator.comparingInt(ProcessControlBlock::getPriority));

        StyledDocument doc = queueTextPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();

        try {
            doc.remove(0, doc.getLength());

            for (ProcessControlBlock pcb : sortedQueue) {
                int priority = pcb.getPriority();
                StyleConstants.setForeground(attr, priorityColors.getOrDefault(priority, Color.BLACK));

                String text = "Process ID: " + pcb.getProcessID() + " | " + pcb.getName() + " | Priority: " + priority + " | Execution Time: " + pcb.getExecutionTime() + " sec\n";
                doc.insertString(doc.getLength(), text, attr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProcessManagerUI().setVisible(true));
    }
}
