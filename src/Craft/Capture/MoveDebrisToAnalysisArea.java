package src.Craft.Capture;

import src.Craft.Analysis.Analyze;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class MoveDebrisToAnalysisArea implements ProcessBase
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
        System.out.println("once debris is captured move it to the analysis area");


        System.out.println("Moving the craft towards debris");
        queue.addProcess(new ProcessControlBlock("From MoveToAnalysis", 3, new Analyze()));


    }



}

