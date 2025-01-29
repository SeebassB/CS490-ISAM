package src;

import src.Craft.Capture.ArmProcess;
import src.Craft.Detection.DetectionProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;

public class Main
{
    public static void main(String[] args)
    {

        System.out.println("starting main");

        ProcessQueue mainQueue = new ProcessQueue();

        mainQueue.addProcess(new ProcessControlBlock("hello",2, new ArmProcess()));

        mainQueue.addProcess(new ProcessControlBlock("world",3, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("mememe",3, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("whatever",0, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("asdasdadas",5, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("four",4, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("zero",0, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("twotwo",2, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("oneone",1, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("threethree",3, new ArmProcess()));

        mainQueue.printQueue();

        mainQueue.executeFirst();

        mainQueue.printQueue();
        mainQueue.executeFirst();

        mainQueue.printQueue();
        mainQueue.executeFirst();

        mainQueue.printQueue();
    }
}
