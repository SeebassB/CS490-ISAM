package src.Craft.Capture;

public class Arm
{
    int currentX;
    int currentY;
    int currentZ;


    public Arm()
    {
        currentX = 0;
        currentY = 0;
        currentZ = 0;
    }

    public Arm(int x, int y, int z)
    {
        currentX = x;
        currentY = y;
        currentZ = z;
    }


    public void printArmPosition()
    {
        System.out.println("current src.arm position: (x = " + currentX + ", y = " + currentY + ", z = " + currentZ + ")");

    }





}
