

public class processControlBlock
{

    private final String name;
    int priority;
    private processControlBlock previous;
    private processControlBlock next;
    private processBase proc;

    public processControlBlock()
    {
        this.name     = "DEFAULT NAME";
        this.priority = 5;
        this.previous = null;
        this.next   = null;
    }


    public processControlBlock(String name, int priority, processBase proc)
    {
        this.name = name;
        this.priority = priority;
        this.previous = null;
        this.next   = null;
        this.proc = proc;
    }

    public processControlBlock(String name, int priority, processControlBlock previous, processControlBlock next )
    {
        this.name = name;
        this.priority = priority;
        this.previous = previous;
        this.next   = next;
    }

    public processControlBlock updateProcess(String name, int priority, processControlBlock next, processControlBlock previous)
    {
        return new processControlBlock(name, priority, next, previous);
    }

    public processControlBlock updateName(String name)
    {
        return new processControlBlock(name, this.priority, this.previous, this.next);
    }

    public processControlBlock updatePriority(int priority)
    {
        return new processControlBlock(this.name, priority, this.previous, this.next);
    }

    public processControlBlock updatePrevious(processControlBlock previous)
    {
        return new processControlBlock(name, this.priority, previous, this.next);
    }

    public processControlBlock updateNext(processControlBlock next)
    {
        return new processControlBlock(name, this.priority, this.previous, next);
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

    public processControlBlock getNext()
    {
        return this.next;
    }

    public void setNext(processControlBlock next)
    {
        this.next = next;
    }

    public processControlBlock getPrevious()
    {
        return this.previous;
    }

    public void setPrevious(processControlBlock previous)
    {
        this.previous = previous;
    }


    public String toString()
    {
        return this.name+", "+this.priority;
    }

    public int compareTo(processControlBlock p)
    {
        return Integer.compare(this.getPriority(), p.getPriority());
    }

    public void execute()
    {
        this.proc.execute();
    }
}
