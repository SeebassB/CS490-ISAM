package src.Craft.ProcessQueue;

public interface ProcessBase
{
    public void printProcess(String input);

    public void logProcess(String input);

    public void logImportant(String input);

    void execute();
}
