package src.Craft.CraftProcesses;

import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class HeartbeatProcess implements ProcessBase
{

    final private ProcessQueue queue = craftThread.getMainQueue();;



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
    public void execute()
    {
        System.out.println("heartbeat executed");

        double test = Math.random();

        if(test>0.5)
            queue.addProcess(new ProcessControlBlock("hello",2, new HeartbeatProcess()));
        else
            queue.addProcess(new ProcessControlBlock("goodbye",1, new HeartbeatProcess()));

    }

}
