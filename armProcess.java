

public class armProcess implements processBase
{





    public boolean moveArmTo(int x, int y, int z)
    {
        System.out.println("moving the armm to ("+x+", "+y+","+z+")");

        return true;
    }

    public void closearm()
    {
        System.out.println("close the arm!");
    }


    @Override
    public void printProcess(String input)
    {
    }

    @Override
    public void logProcess(String input)
    {
        System.out.println("arm - " + input);
    }



    @Override
    public void logImportant(String input)
    {
        System.out.println("arm - important - " + input);
    }

    public void execute()
    {
        System.out.println("EXECUTING ARM PROCESS");

    }
}
