package data;

import org.junit.Test;
import util.Bookshelf;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSVReaderTest {

    Bookshelf shelf = Bookshelf.getShelf();

    @Test
    public void readBook() throws IOException {
        CSVReader testA = new CSVReader("test1.csv");
        testA.readBook();
        assertFalse(shelf.getBooks().get("comic").isEmpty());
//        assertEquals(9,shelf.getBooks().get("fiction").size());
    }

    @Test
    public void newBook() {
    }
}