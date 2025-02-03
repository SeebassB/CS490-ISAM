package src.Craft.ProcessQueue;

public class ProcessControlBlock
{

    private final String name;
    int priority;
    private ProcessControlBlock previous;
    private ProcessControlBlock next;
    private ProcessBase proc;

    public ProcessControlBlock()
    {
        this.name     = "DEFAULT NAME";
        this.priority = 5;
        this.previous = null;
        this.next   = null;
    }


    public ProcessControlBlock(String name, int priority, ProcessBase proc)
    {
        this.name = name;
        this.priority = priority;
        this.previous = null;
        this.next   = null;
        this.proc = proc;
    }

    public ProcessControlBlock(String name, int priority, ProcessControlBlock previous, ProcessControlBlock next )
    {
        this.name = name;
        this.priority = priority;
        this.previous = previous;
        this.next   = next;
    }

    public ProcessControlBlock updateProcess(String name, int priority, ProcessControlBlock next, ProcessControlBlock previous)
    {
        return new ProcessControlBlock(name, priority, next, previous);
    }

    public ProcessControlBlock updateName(String name)
    {
        return new ProcessControlBlock(name, this.priority, this.previous, this.next);
    }

    public ProcessControlBlock updatePriority(int priority)
    {
        return new ProcessControlBlock(this.name, priority, this.previous, this.next);
    }

    public ProcessControlBlock updatePrevious(ProcessControlBlock previous)
    {
        return new ProcessControlBlock(name, this.priority, previous, this.next);
    }

    public ProcessControlBlock updateNext(ProcessControlBlock next)
    {
        return new ProcessControlBlock(name, this.priority, this.previous, next);
    }


    public String getName()
    {
        return this.name;
    }

    public int getPriority()
    {
        return this.priority;
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


    public String toString()
    {
        return this.name+", "+this.priority;
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
