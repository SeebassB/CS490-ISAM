package src.Craft.Detection;

import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class ScanWithCamera implements ProcessBase
{

    final private ProcessQueue queue = craftThread.getMainQueue();


    @Override
    public void printProcess(String input) {

    }

    @Override
    public void logProcess(String input) {

    }

    @Override
    public void logImportant(String input) {

    }

    @Override
    public void execute() {
        System.out.println("scan for debris");

        //generate a random number for this test
        double test = Math.random();

        if(test>0.2)
        {
            System.out.println("Debris Detected");
            queue.addProcess(new ProcessControlBlock("From ScanWithCamera", 2, new DetermineDebrisValidity()));
        }
        else
        {
            System.out.println("No Debris Detected");
            System.out.println("Resetting back to heartbeat");
            queue.addProcess(new ProcessControlBlock("From ScanWithCamera", 5, new HeartbeatProcess()));
        }

    }



}

