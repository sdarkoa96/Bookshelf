package logging;

import data.CSVReader;
import org.junit.Before;
import org.junit.Test;
import util.Bookshelf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoggerTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Logger l = Logger.getInstance();
    CSVReader csvReader = new CSVReader(l, shelf);

//    @Before
//    public void setUp() throws Exception {
//    }

    @Test
    public void destination() throws IOException {
        l.setDestination("test_log");
        csvReader.readBook("test1.csv");

        FileReader fr = new FileReader("test_log");
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> destArray = new ArrayList<>();
        String line = null;

        while((line = br.readLine()) != null){
            destArray.add(line);
        }


        assertEquals(12, destArray.size());

        csvReader.readBook("test1.csv");

        while((line = br.readLine()) != null){
            destArray.add(line);
        }

        assertEquals(24, destArray.size());

        fr.close();
        br.close();

    }
}