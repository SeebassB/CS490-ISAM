package src.Craft.Capture;

import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class Store implements ProcessBase
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
        System.out.println("Store the debris in the correct storage container");


        System.out.println("Then reset back to heartbeat");
        queue.addProcess(new ProcessControlBlock("From Store", 3, new HeartbeatProcess()));


    }



}

