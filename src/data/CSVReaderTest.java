package data;

import org.junit.Before;
import org.junit.Test;
import util.Bookshelf;
import logging.Logger;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSVReaderTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Logger l = Logger.getInstance();

    @Before
    public void setUp() throws IOException {
        l.setDestination();
        if(!shelf.getBooks().get("fiction").isEmpty()){
            shelf.getBooks().get("fiction").clear();
        }

        if(!shelf.getBooks().get("non-fiction").isEmpty()){
            shelf.getBooks().get("non-fiction").clear();
        }

        if(!shelf.getBooks().get("comic").isEmpty()){
            shelf.getBooks().get("comic").clear();
        }

    }

    @Test(expected = Exception.class)
    public void readBook() throws IOException, NullPointerException {
        CSVReader testA = new CSVReader(l,shelf);
        testA.readBook("test1.csv");
        assertFalse(shelf.getBooks().get("comic").isEmpty());
        assertEquals(7,shelf.getBooks().get("fiction").size());

        testA.readBook(null); //throws exception


        testA.readBook("test2.csv");
        assertEquals(7,shelf.getBooks().get("fiction").size());

        CSVReader testB = new CSVReader(l,shelf);
        testB.readBook("testempty.csv");



    }

    @Test
    public void readBookEmpty() throws IOException, NullPointerException {
        CSVReader testB = new CSVReader(l,shelf);
        testB.readBook("testempty.csv");
        assertEquals(1,shelf.getBooks().get("fiction").size());
        assertEquals(0,shelf.getBooks().get("non-fiction").size());

    }

    @Test
    public void newBook() {
        CSVReader testA = new CSVReader(l,shelf);
        testA.newBook("Tatsuki Fujimoto","Chainsaw Man","comic","Chainsaw Man",6,"false",0);
        assertFalse(shelf.getBooks().get("comic").isEmpty());
    }
}