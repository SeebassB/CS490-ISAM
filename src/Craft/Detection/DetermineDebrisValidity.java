package src.Craft.Detection;

import src.Craft.CraftProcesses.AdjustTrajectory;
import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class DetermineDebrisValidity implements ProcessBase
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
        System.out.println("determine whether debris is viable");

        //generate a random number for this test
        double test = Math.random();

        if(test>0.2)
        {
            System.out.println("Debris Valid");
            queue.addProcess(new ProcessControlBlock("From DetermineDebrisValidity", 3, new AdjustTrajectory()));
        }
        else
            System.out.println("Debris INValid");
        queue.addProcess(new ProcessControlBlock("From DetermineDebrisValidity",5, new HeartbeatProcess()));


    }



}

