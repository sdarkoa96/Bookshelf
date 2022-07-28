import data.CSVReader;
import data.Input;
import logging.Logger;
import processor.Pull;
import processor.Update;
import ui.Output;
import util.Bookshelf;
import write.Writer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        Logger logger = Logger.getInstance();

        //TODO: have user set destination of logger
        Bookshelf shelf = Bookshelf.getShelf();

        CSVReader csv = new CSVReader(logger,shelf);
        Input input = new Input(logger,shelf);
        Pull pull = new Pull(shelf);
        Update update = new Update(logger,shelf);
        Writer writer = new Writer(shelf);
        Output output = new Output(csv,input,pull,update,writer,shelf);


        try{
            output.execute();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
