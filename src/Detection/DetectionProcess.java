package src;

public class DetectionProcess implements processBase
{

    public void scanIR()
    {
        System.out.println("detection - Scanning IR");
    }

    public void scanRadar()
    {
        System.out.println("detection - Scanning Radar");
    }

    public void scanCamera()
    {
        System.out.println("detection - Scanning Camera");
    }

    public void detectGeometry()
    {
        System.out.println("detection - Detecting Geometry");
    }

    public void getDebrisLocation()
    {
        System.out.println("detection - Getting Debris Location");
    }





    @Override
    public void printProcess(String input) {

    }

    @Override
    public void logProcess(String input) {

    }

    @Override
    public void logImportant(String input) {

    }

    public void execute()
    {


    }

}
