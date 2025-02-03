package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;

import javax.swing.*;

public class main
{
    public static void main(String[] args)
    {
        craftThread testThread = new craftThread();
        testThread.run();

        testThread.addProceesToCraft(new ProcessControlBlock("fromMain", 3, new ArmProcess()));
     //add window to display queue and also with a textfield to input events that go into the queue

    }
}
