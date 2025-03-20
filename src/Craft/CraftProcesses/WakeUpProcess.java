package src.Craft.CraftProcesses;

import src.Craft.ProcessQueue.ProcessBase;
import src.Craft.ProcessQueue.ProcessControlBlock;
import src.Craft.ProcessQueue.ProcessQueue;
import src.Craft.craftThread;

public class WakeUpProcess implements ProcessBase
{

        final private ProcessQueue queue = craftThread.getMainQueue();


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
        public void execute() {
            System.out.println("this process runs first after launch");
            System.out.println("Startup all systems and prepare the craft for main loop");
            queue.addProcess(new ProcessControlBlock("From Wakeup",5, new HeartbeatProcess()));

        }



}

