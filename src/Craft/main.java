package src.Craft;

import src.Craft.Capture.ArmProcess;
import src.Craft.ProcessQueue.ProcessControlBlock;

import javax.swing.*;

public class main extends JFrame
{

    JTextField textField1;
    JButton mybutton;

    public main()
    {

    }


    public static void main(String[] args)
    {

        craftThread testThread = new craftThread();

        Thread thr = new Thread(testThread);
        thr.start();

        //start the GUI


    }
}
