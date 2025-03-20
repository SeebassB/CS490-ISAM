package src.Craft.CraftProcesses;

import src.Craft.Capture.MoveArm;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class AdjustTrajectory implements ProcessBase
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
        System.out.println("once debris is determined adjust the craft's trajectory to go get it");


            System.out.println("Moving the craft towards debris");
            queue.addProcess(new ProcessControlBlock("From AdjustTrajectory", 3, new MoveArm()));


    }



}

