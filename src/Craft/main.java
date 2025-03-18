package src.Craft;


import javax.swing.*;

public class main extends JFrame
{



    public static void main(String[] args)
    {

        craftThread testThread = new craftThread();

        Thread thr = new Thread(testThread);
        thr.start();

        //start the GUI


    }
}
