package write;

import data.CSVReader;
import logging.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.Bookshelf;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class WriterTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Logger logger = Logger.getInstance();
    CSVReader csvReader = new CSVReader(logger,shelf);

    @Before
    public void setUp() throws IOException {
        logger.setDestination();
        csvReader.readBook("test1.csv");

    }

    @Test
    public void wrtLn() {

    }

//    @Rule
//    public ExpectedException nullRule = ExpectedException.class();

    @Test(expected = Exception.class)
    public void write() throws Exception {
        Writer test = new Writer(shelf);
        test.write("wrtTest.csv");
        test.write(null);

        test = new Writer(null);
        test.write("empty_file.csv");
    }
}