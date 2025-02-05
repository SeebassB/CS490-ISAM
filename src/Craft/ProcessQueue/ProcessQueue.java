package src.Craft.ProcessQueue;


public class ProcessQueue
{
    ProcessControlBlock head;
    ProcessControlBlock tail;
    int processID ;


    public ProcessQueue()
    {
        this.head = null;
        this.tail = null;
        this.processID = 0;
    }

    /**
     * Adds a process to the ProcessQueue
     * @param toBeAdded ProcessControlBlock you want to add to the ProcessQueue
     * @return boolean pass or fail of the addition
     * */
    public boolean addProcess(ProcessControlBlock toBeAdded)
    {

        toBeAdded.updateProcessID(this.processID);
        this.processID++;

        System.out.println("TESTING PROCESSID UPDATE "+this.processID);

        System.out.println("Adding process: \"" + toBeAdded.toString() + "\"");

        //check if the list is empty
        if(this.head == null)
        {
            this.head = toBeAdded;
            this.tail = toBeAdded;
            System.out.println("ADDPROCESS " + toBeAdded.getName() + " at position 0 since the queue is empty\n");
            return true;
        }

        ProcessControlBlock current = head;
        ProcessControlBlock secondary = null;

        //iterate through the list to find the proper priority insert
        while(current != null)
        {
            //find the first element that is higher in priority number
            if(toBeAdded.getPriority() < current.getPriority())
            {

                //check to see if this is the new head
                if(secondary == null)
                {

                    head.setPrevious(toBeAdded);
                    toBeAdded.setNext(this.head);
                    head = toBeAdded;
                }
                else //not the first card
                {

                    secondary.setNext(toBeAdded);
                    toBeAdded.setPrevious(secondary);
                    current.setPrevious(toBeAdded);
                    toBeAdded.setNext(current);
                }
                return true;
            }

            //go to the next in the list
            secondary = current;
            current = current.getNext();

        }
        //new insert is the new tail
        // System.out.println("TAIL= "+current.getName());

        tail.setNext(toBeAdded);
        toBeAdded.setPrevious(tail);
        tail = toBeAdded;
        System.out.println("ADDPROCESS " + toBeAdded.getName() + " at position ? since the queue is NOT EMPTY\n");

        return true;
    }


    /**
     * Executes the first process in the ProcessQueue
     * checks to see if the first block is Null
     * @return ProcessControlBlock the process being executed, when null returns NULL
     * */
    public ProcessControlBlock executeFirst()
    {

     if(head == null)
     {
         System.out.println("Execute: list is empty");
         return tail;

     }

        //save head and remove it from list
        ProcessControlBlock temp = head;
        head = head.getNext();
        temp.execute();
        return temp;
    }

    /**
     * Prints the current queue
     * No return, prints into Console
     * */
    public void printQueue()
    {

        ProcessControlBlock temp = head;

        System.out.println("----------PRINTING QUEUE-------");

        while(temp != null)
        {

            System.out.println(temp.toString());

            temp = temp.getNext();
        }
    }


    /**
     * Check the ProcessQueue to see if its empty
     * @return boolean whether the list is empty
     * */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Get the head of the ProcessQueue
     * @return ProcessControlBlock the PCB that is the head
     * */
    public ProcessControlBlock getHead()
    {
        return head;
    }

    /**
     * Get the tail of the ProcessQueue
     * @return ProcessControlBlock the PCB that is the tail
     * */
    public ProcessControlBlock getTail()
    {
        return tail;
    }
}

