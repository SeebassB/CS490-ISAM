package src.Craft.Detection;

import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class DetectionProcess implements ProcessBase{

    final private ProcessQueue queue = craftThread.getMainQueue();;

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


    @Override
    public void execute()
    {


    }

}
