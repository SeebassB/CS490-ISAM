public class Main
{
    public static void main(String[] args)
    {

        System.out.println("starting main");

        processQueue mainQueue = new processQueue();

        mainQueue.addProcess(new processControlBlock("hello",2, new armProcess()));

        mainQueue.addProcess(new processControlBlock("world",3, new detectionProcess()));
        mainQueue.addProcess(new processControlBlock("mememe",3, new detectionProcess()));
        mainQueue.addProcess(new processControlBlock("whatever",0, new detectionProcess()));
        mainQueue.addProcess(new processControlBlock("asdasdadas",5, new armProcess()));
        mainQueue.addProcess(new processControlBlock("four",4, new armProcess()));
        mainQueue.addProcess(new processControlBlock("zero",0, new armProcess()));
        mainQueue.addProcess(new processControlBlock("twotwo",2, new armProcess()));
        mainQueue.addProcess(new processControlBlock("oneone",1, new armProcess()));
        mainQueue.addProcess(new processControlBlock("threethree",3, new armProcess()));

        mainQueue.printQueue();

        mainQueue.executeFirst();

        mainQueue.printQueue();
        mainQueue.executeFirst();

        mainQueue.printQueue();
        mainQueue.executeFirst();

        mainQueue.printQueue();
    }
}
