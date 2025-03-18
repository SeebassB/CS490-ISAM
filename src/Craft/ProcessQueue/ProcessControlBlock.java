package src.Craft.ProcessQueue;

public class ProcessControlBlock
{

    private final String name;
    int priority;
    int processID;
    private ProcessControlBlock previous;
    private ProcessControlBlock next;
    private ProcessBase proc;
    private ProcessQueue queue;


    /**
     * Barebones PCB creator
     * */
    public ProcessControlBlock(String name, int priority, ProcessBase proc)
    {
        this.name = name;
        this.priority = priority;
        this.previous = null;
        this.next   = null;
        this.proc = proc;
    }

    /**
     * Full PCB Constructor
     * */
    public ProcessControlBlock(String name, int priority,int ID, ProcessControlBlock previous, ProcessControlBlock next)
    {
        this.name = name;
        this.priority = priority;
        this.processID = ID;
        this.previous = previous;
        this.next   = next;
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
