package Craft.Analysis;

import Craft.ProcessQueue.ProcessBase;


public class AnalysisProcess implements ProcessBase {
    
    public AnalysisProcess() {
        System.out.println("AnalysisProcess: Initialized.");
    }

    @Override
    public void execute() {
        System.out.println("EXECUTING ANALYSIS PROCESS");

        StagingConfirmation();
        verifyPosition();
        performPhotogrametry();
        collectPhotogrammetryMetadata();

        storeToDatabase(); // Placeholder for now, real DB code will go here later.
    }
   
    /*  printProcess method will used to log messages to the console regarding debugging
    This may be the entry point for the GUI, or a log file */
    @Override
    public void printProcess(String input) {
        System.out.println("AnalysisProcess Log: " + input);
    }

/*     Note: This method does not retrieve or print from logProcess().
It simply prints the provided input to the console with an 
"AnalysisProcess Log:" prefix. If logs need to be printed from a stored location,
that would require additional implementation. */
    @Override
    public void logProcess(String input) {
        System.out.println("AnalysisProcess - Log: " + input);
    }

/*     Logs critical information with "AnalysisProcess - IMPORTANT:" prefix.
    Might later be used for alerts or flagged logs.
     */
    @Override
    public void logImportant(String input) {
        System.out.println("AnalysisProcess - IMPORTANT: " + input);
    }

    /*  StagingConfirmation *signal based* will be used to confirm that the staging
    process has been completed.*/
    public void StagingConfirmation() {
        System.out.println("AnalysisProcess: Staging Confirmation");
    }

    public void verifyPosition(){
        System.out.println("AnalysisProcess: Verifying Position");
    }

    /*  This method will be used to perform photogrametry, will incorporate motor/camera control
    using a python wrapper, i hope */
    public void performPhotogrametry(){
        System.out.println("AnalysisProcess: Performing Photogrametry");
    }

    public void collectPhotogrammetryMetadata(){
        System.out.println("AnalysisProcess: Collecting Photogrammetry Metadata");
    }
    
    /* will create a very small SQLite database to store metadata, will need to document
    scalability for ISAM, not needed for presenataion*/
    public void storeToDatabase(){
        System.out.println("AnalysisProcess: Storing to Database");
    }



}
