package src.Craft.ProcessQueue;

import src.Craft.Capture.Store;

public class ProcessControlBlock
{
    private static int idCounter = 1; // Unique ID counter
    private int processID;
    private final String name;
    int priority;
    private int executionTime; // New attribute for execution time
    private ProcessControlBlock previous;
    private ProcessControlBlock next;
    private ProcessBase proc;
    private ProcessQueue queue;


    /**
     * Default PCB creator
     * */
    public ProcessControlBlock()
    {
        this.name     = "DEFAULT NAME";
        this.priority = 5;
        this.processID = -1;
        this.executionTime = 10; // Default execution time
        this.previous = null;
        this.next   = null;
    }

    /**
     * Barebones PCB creator
     * */
    public ProcessControlBlock(String name, int priority, int executionTime, ProcessBase proc) {
        this.processID = idCounter++;
        this.name = name;
        this.priority = priority;
        this.executionTime = executionTime;
        this.proc = proc;
        this.previous = null;
        this.next = null;
    }

    /**
     * Full PCB Constructor
     */
    public ProcessControlBlock(String name, int priority, int executionTime, ProcessControlBlock previous, ProcessControlBlock next) {
        this.processID = idCounter++;

        this.name = name;
        this.priority = priority;
        this.executionTime = executionTime;
        this.previous = previous;
        this.next = next;
    }

    public ProcessControlBlock(String fromAnalyze, int i, ProcessBase store)
    {
        this.processID = idCounter++;

        this.name = fromAnalyze;
        this.priority = i;
        this.executionTime = 5;
        this.proc = store;
    }


    public void updateProcessID(int processID)
    {
        this.processID = processID;
    }






    public String getName()
    {
        return this.name;
    }

    public int getPriority()
    {
        return this.priority;
    }

    public int getProcessID()
    {
        return this.processID;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public ProcessControlBlock getNext()
    {
        return this.next;
    }

    public void setNext(ProcessControlBlock next)
    {
        this.next = next;
    }

    public ProcessControlBlock getPrevious()
    {
        return this.previous;
    }

    public void setPrevious(ProcessControlBlock previous)
    {
        this.previous = previous;
    }


    public ProcessQueue getQueue()
    {
        return queue;
    }
      
    public int getExecutionTime() {
        return this.executionTime;

    }

    public String toString()
    {
        return this.name + ", " + this.priority + ", " + this.processID;
    }

    public String detailedToString()
    {

        return this.name + ", " + this.priority + ", " + this.processID +", "+this.previous +","+this.next;
    }


    public int compareTo(ProcessControlBlock p)
    {
        return Integer.compare(this.getPriority(), p.getPriority());
    }

    public void execute()
    {
        System.out.println("Executing process: "+this.toString());
        this.proc.execute();
    }
}
