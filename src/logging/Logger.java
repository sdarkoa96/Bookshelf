package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private PrintWriter out;
    public String destination = null;
    private FileWriter logWrite;
    private Logger(){}
    private static Logger instance = new Logger();
    public static Logger getInstance(){return instance;}

    //logs event to file
    public void logEvent(String event) {
        this.out.print(System.currentTimeMillis()+" ");
        this.out.print(event);
        this.out.println();
        this.out.flush();
    }

    //set or changes output destination
    public void setDestination(String filename) throws IOException, SecurityException, NullPointerException {
        boolean append = true; //boolean to determine whether or not to append to a file

        //checks whether writing to a new file or old one
        if(this.destination == null){
            this.destination = filename;
        }else if(!(this.destination.equals(filename))){
            append = false;
            logWrite.close();
            out.close();

        }
        File logFile = new File(filename);
        logWrite = new FileWriter(logFile,append);
        out = new PrintWriter(logWrite);

    }

    //writes to System.err when file not specified in command line
    public void setDestination() throws IOException, SecurityException, NullPointerException {
        if (this.destination == null||this.destination.isBlank()) {
            this.out = new PrintWriter(System.err);
        } else{
            logWrite.close();
            this.out.close();
            this.out = new PrintWriter(System.err);
        }
    }
}
