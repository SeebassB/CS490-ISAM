package src.Craft.Capture;

import src.Craft.ProcessQueue.ProcessBase;

public class ArmProcess implements ProcessBase {





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

    }
}
