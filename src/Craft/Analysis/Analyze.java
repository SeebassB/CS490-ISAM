package src.Craft.Analysis;

import src.Craft.Capture.Store;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class Analyze implements ProcessBase
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
        System.out.println("Analyze the debris and determine where it should be stored");


        queue.addProcess(new ProcessControlBlock("From Analyze", 3, new Store()));


    }



}

