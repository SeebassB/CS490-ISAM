package src.Craft.Capture;

import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class ArmProcess implements ProcessBase {

    final private ProcessQueue queue = craftThread.getMainQueue();



    public boolean moveArmTo(int x, int y, int z)
    {
        System.out.println("moving the armm to ("+x+", "+y+","+z+")");

        return true;
    }

    public void closearm()
    {
        System.out.println("close the src.arm!");
    }


    @Override
    public void printProcess(String input)
    {
    }

    @Override
    public void logProcess(String input)
    {
        System.out.println("src.arm - " + input);
    }



    @Override
    public void logImportant(String input)
    {
        System.out.println("src.arm - important - " + input);
    }

    public void execute()
    {
        System.out.println("EXECUTING ARM PROCESS");
        //
        //
        //
        //
        //
        queue.addProcess(new ProcessControlBlock("goodbye",1, new HeartbeatProcess()));


    }


}
