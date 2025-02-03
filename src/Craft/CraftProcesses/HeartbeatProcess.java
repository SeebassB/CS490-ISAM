package src.Craft.CraftProcesses;

import src.Craft.ProcessQueue.ProcessBase;

public class HeartbeatProcess implements ProcessBase
{





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
System.out.println("heartbeat executed");
    }
}
