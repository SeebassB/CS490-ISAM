package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.Detection.DetectionProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;

import javax.swing.*;
import java.lang.Math;




/**
 * Main thread that runs in the craft, should only be one of the ever running at a time
 * */
public class craftThread implements Runnable
{
    private static final ProcessQueue mainQueue = new ProcessQueue();

    private ProcessQueue queue;
    private ProcessManagerUI ui;
    private volatile boolean isRunning = true; // Controls execution state
    /**
     * This is the priority queue of the craft.
     * This is used to hold the current tasks that the craft has to do.
     */
    //private static final ProcessQueue mainQueue = new ProcessQueue();



    public craftThread(ProcessQueue queue, ProcessManagerUI ui)
    {
        this.queue = queue;
        this.ui = ui;
    }

    public void run() {
        while (true) {
            if (isRunning && !mainQueue.isEmpty()) {
                ProcessControlBlock process = mainQueue.executeFirst();
                if (process != null) {
                    ui.setCurrentProcess(process);
                    ui.logMessage("Executing: " + process.getName());
                    executeWithProgress(process);
                }
                ui.updateQueueDisplay();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void executeWithProgress(ProcessControlBlock pcb) {
        int duration = pcb.getExecutionTime();
        int steps = 10;
        int delay = duration * 1000 / steps;

        for (int i = 0; i <= steps; i++) {
            if (!ui.isRunning()) return;

            // Time left (approx)
            int secondsLeft = duration - (i * duration / steps);

            // Wrap the UI update in invokeLater
            final int finalSecondsLeft = secondsLeft;
            SwingUtilities.invokeLater(() -> {
                ui.updateTimeLeft(pcb, finalSecondsLeft);
            });

            // Also update the progress bar from the EDT
            final int progressValue = i * 10;
            SwingUtilities.invokeLater(() -> {
                ui.updateProgress(pcb, progressValue);
            });

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        SwingUtilities.invokeLater(() -> {
            ui.updateProgress(pcb, 100);
            ui.updateTimeLeft(pcb, 0);
        });
    }


    public void setRunning(boolean running) {
        this.isRunning = running;
    }
    /*
     * The beginning of the thread that runs the craft
     * */

    /*
    public void run()
    {

        System.out.println("starting craft thread");

       // ProcessQueue mainQueue = new ProcessQueue();

       // mainQueue.addProcess(new ProcessControlBlock("hello",2, new ArmProcess(), mainQueue));

        //mainQueue.addProcess(new ProcessControlBlock("world",3,  new DetectionProcess(), mainQueue));
        //mainQueue.addProcess(new ProcessControlBlock("mememe",3, new DetectionProcess(), mainQueue));
        //mainQueue.addProcess(new ProcessControlBlock("whatever",5, new DetectionProcess(), mainQueue));
        //mainQueue.addProcess(new ProcessControlBlock("asdasdadas",4, new ArmProcess(), mainQueue));
        //mainQueue.addProcess(new ProcessControlBlock("four",4, new ArmProcess(), mainQueue));
        //mainQueue.addProcess(new ProcessControlBlock("zero",0, new ArmProcess(), mainQueue));
        mainQueue.addProcess(new ProcessControlBlock("twotwo",2, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("oneone",1, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("threethree",3, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("heartbeat", 5, new HeartbeatProcess()));



        /**
         * Internal loop used to capture the craft's thread.
         * Puts a "dummy" process onto an empty stack to keep the craft running
         *
        while(true)
        {


            mainQueue.printQueue();

            mainQueue.executeFirst();

            //one second delay in the while loop, for stability, can be easily lowered
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            //check to see if the queue is empty after executing, when empty put said dummy heartbeat process
            if(mainQueue.isEmpty())
            {
                mainQueue.addProcess(new ProcessControlBlock("heartbeat", 5, new HeartbeatProcess()));
            }

        }


        /**
         * This method is used when adding a process to the craft "Externally"
         * Will be used exclusively by the simulator
         * @param newProcess the process you want to add into the queue
         * */
        public boolean addProceesToCraft(ProcessControlBlock newProcess)
        {
            mainQueue.addProcess(newProcess);
            return true;
        }

        public static ProcessQueue getMainQueue()
        {
            return mainQueue;
        }

}

