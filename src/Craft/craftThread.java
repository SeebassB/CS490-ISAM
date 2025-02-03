package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.CraftProcesses.HeartbeatProcess;
import src.Craft.Detection.DetectionProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import java.lang.Math;


public class craftThread implements Runnable
{
    public void run()
    {

        System.out.println("starting craft thre");

        ProcessQueue mainQueue = new ProcessQueue();

        mainQueue.addProcess(new ProcessControlBlock("hello",2, new ArmProcess()));

        mainQueue.addProcess(new ProcessControlBlock("world",3, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("mememe",3, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("whatever",0, new DetectionProcess()));
        mainQueue.addProcess(new ProcessControlBlock("asdasdadas",4, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("four",4, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("zero",0, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("twotwo",2, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("oneone",1, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("threethree",3, new ArmProcess()));
        mainQueue.addProcess(new ProcessControlBlock("heartbeat", 5, new HeartbeatProcess()));


        int i=0;

        while(true)
        {

            if(Math.random() > 0.9)
            {
                mainQueue.addProcess(new ProcessControlBlock("random insert",1, new ArmProcess()));
            }

            mainQueue.printQueue();

                mainQueue.executeFirst();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(mainQueue.isEmpty()) {
                mainQueue.addProcess(new ProcessControlBlock("heartbeat", 5, new HeartbeatProcess()));
                System.out.println("adding heartbeat to an empty stack: "+i);
            }

        i++;
        }

        }

        public boolean addProceesToCraft(ProcessControlBlock newProcess)
        {
            return true;
        }
}

