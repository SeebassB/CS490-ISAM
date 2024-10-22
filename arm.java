
public class arm
{
    int currentX;
    int currentY;
    int currentZ;


    public arm()
    {
        currentX = 0;
        currentY = 0;
        currentZ = 0;
    }

    public arm(int x, int y, int z)
    {
        currentX = x;
        currentY = y;
        currentZ = z;
    }


    public void printArmPosition()
    {
        System.out.println("current arm position: (x = " + currentX + ", y = " + currentY + ", z = " + currentZ + ")");

    }





}
