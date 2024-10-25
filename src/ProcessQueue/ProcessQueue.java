package src;


public class ProcessQueue
{
    ProcessControlBlock head;
    ProcessControlBlock tail;

    public ProcessQueue()
    {
        this.head = null;
        this.tail = null;
    }

    //assumes the list is sorted
    boolean addProcess(ProcessControlBlock toBeAdded)
    {
        System.out.println("Adding process: \"" + toBeAdded.toString() + "\"");

        //check if the list is empty
        if(this.head == null)
        {
            this.head = toBeAdded;
            this.tail = toBeAdded;
            System.out.println("\nADDPROCESS " + toBeAdded.getName() + " at position 0 since the queue is empty");
            return true;
        }

        ProcessControlBlock current = head;
        ProcessControlBlock secondary = null;

        //iterate through the list to find the proper priority insert
        while(current != null)
        {
            System.out.println("WHILE= "+current.getName());
            //find the first element that is higher in priority number
            if(toBeAdded.getPriority() < current.getPriority())
            {
                System.out.println("FIRST IF= "+current.getName());

                //check to see if this is the new head
                if(secondary == null)
                {
                    System.out.println("SECONDARY IF= "+current.getName());

                    head.setPrevious(toBeAdded);
                    toBeAdded.setNext(this.head);
                    head = toBeAdded;
                }
                else //not the first card
                {
                    System.out.println("SECONDARY ELSE= "+current.getName());

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

        return true;
    }

    public ProcessControlBlock executeFirst()
    {
        //save head and remove it from list
        ProcessControlBlock temp = head;
        head = head.getNext();
        temp.execute();
        return temp;
    }


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






    public ProcessControlBlock getHead()
    {
        return head;
    }

    public ProcessControlBlock getTail()
    {
        return tail;
    }


}

