package data;

import org.junit.Before;
import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Bookshelf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Input testIn = new Input();
//    @Before
//    public void setUp(){
//         testIn = new Input();
//    }

    @Test
    public void testNewBook(){


        this.testIn.newBook("Octavia E. Butler","Kindred",
                "fiction",null);
        Assert.assertFalse(shelf.getBooks().isEmpty());
    }

    @Test
    public void testInputBook(){
        //figure out if it's possible to test this
//        String author = "N.K Jemisin";
//        String title = "The Fifth Season";
//        Integer type = 1;
//        char series = 'y';
//        String seriesTitle = "The Broken Earth Triology";
//        int vol = 1;
//
//        InputStream in = new ByteArrayInputStream(author.getBytes());
//        System.setIn(in);
//        in = new ByteArrayInputStream(title.getBytes());
//        System.setIn(in);
//        in = new ByteArrayInputStream(type);
//        System.setIn(in);
//        this.testIn.inputBook();
//        Assert.assertFalse(shelf.getBooks().isEmpty());

    }

}