import data.CSVReader;
import data.Input;
import logging.Logger;
import processor.Pull;
import processor.Update;
import ui.Output;
import util.Bookshelf;
import write.Writer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Logger logger = Logger.getInstance();

        //TODO: have user set destination of logger
        Scanner scanner = new Scanner(System.in);
        String ans = null;
        String logFile = null;
        System.out.println("Would like to create a log file Y/N?");
        ans = scanner.next();

        if(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")){
            System.out.println("Enter a name for the log file:");
            logFile = scanner.next();
            logger.setDestination(logFile);
        }else {
            logger.setDestination();
        }

        Bookshelf shelf = Bookshelf.getShelf();

        CSVReader csv = new CSVReader(logger,shelf);
        Input input = new Input(logger,shelf);
        Pull pull = new Pull(shelf);
        Update update = new Update(logger,shelf);
        Writer writer = new Writer(shelf);
        Output output = new Output(csv,input,pull,update,writer,shelf,scanner);


        try{
            output.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        scanner.close();


    }

}
