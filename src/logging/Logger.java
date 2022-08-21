package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs events of program
 */
public class Logger {
    /**
     * writes to output file
     */
    private PrintWriter out;
    /**
     * holds name of file to be created
     */
    String destination = null;
    private FileWriter logWrite;

    /**
     * ensure only one logger object will be created
     */
    private Logger(){}
    private static Logger instance = new Logger();
    public static Logger getInstance(){return instance;}

    /**
     * logs events
     * @param event string to enter into log
     */
    public void logEvent(String event) {
        this.out.print(System.currentTimeMillis()+" ");
        this.out.print(event);
        this.out.println();
        this.out.flush();
    }

    /**
     * set or changes output destination of log file
     * @param filename name to give log file
     * @throws IOException
     * @throws SecurityException
     * @throws NullPointerException
     */
    public void setDestination(String filename) throws IOException, SecurityException, NullPointerException {
        boolean append = true; //boolean to determine whether to append to a file

        //checks whether writing to a new file or old one
        if(filename.isBlank()){
            setDestination();
        }else if(this.destination == null){
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

    /**
     * writes to System.err when file not specified
     * @throws IOException
     * @throws SecurityException
     * @throws NullPointerException
     */
    public void setDestination() throws IOException, SecurityException, NullPointerException {
        if (this.destination != null && !this.destination.isBlank()) {
            logWrite.close();
            this.out.close();
        }
        this.out = new PrintWriter(System.err);
    }
}
