package src.Craft.Capture;

import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class MoveArm implements ProcessBase
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
        System.out.println("Moving arm to capture debris");


            queue.addProcess(new ProcessControlBlock("From DetermineDebrisValidity", 3, new MoveDebrisToAnalysisArea()));


    }



}

