package data;

import org.junit.Test;
import util.Bookshelf;
import logging.Logger;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSVReaderTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Logger l = Logger.getInstance();

    @Test(expected = Exception.class)
    public void readBook() throws IOException, NullPointerException {
        CSVReader testA = new CSVReader();
        testA.readBook("test1.csv");
        assertFalse(shelf.getBooks().get("comic").isEmpty());
        assertEquals(7,shelf.getBooks().get("fiction").size());

        testA.readBook(null);


    }

    @Test
    public void newBook() {
        CSVReader testA = new CSVReader();
        testA.newBook("Tatsuki Fujimoto","Chainsaw Man","comic","Chainsaw Man",6,"false");
        assertFalse(shelf.getBooks().get("comic").isEmpty());
    }
}