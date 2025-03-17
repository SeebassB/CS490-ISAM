package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;

import javax.swing.*;

public class main
{
    public main()
    {

    }

/*
    public static void main(String[] args)
    {

        craftThread testThread = new craftThread();

        Thread thr = new Thread(testThread);
        thr.start();

        //start the GUI


    }
    */

    public static void main(String[] args) {
        // Ensure GUI starts on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            ProcessManagerUI ui = new ProcessManagerUI();
            ui.setVisible(true);
        });
    }
}
