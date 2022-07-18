import data.CSVReader;
import data.Input;
import processor.Pull;
import processor.Update;
import ui.Output;
import write.Writer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        CSVReader csv = new CSVReader();
        Input input = new Input();
        Pull pull = new Pull();
        Update update = new Update();
        Writer writer = new Writer();
        Output output = new Output(csv,input,pull,update,writer);

        output.execute();
    }

}
