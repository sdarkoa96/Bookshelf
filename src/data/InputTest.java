package data;

import org.junit.Before;
import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Book;
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

        Book book1 = new Book("Octavia E. Butler","Kindred",
                "fiction",null);
        Book book2 = new Book("Octavia E. Butler","Kindred",
                "fiction",null);

        Assert.assertEquals(book1,book2);

        this.testIn.newBook("Octavia E. Butler","Kindred",
                "fiction",null);
        Assert.assertFalse(shelf.getBooks().get("fiction").isEmpty());
        this.testIn.newBook("Octavia E. Butler","Kindred",
                "fiction",null);
        Assert.assertEquals(shelf.getBooks().get("fiction").size(),1);
        this.testIn.newBook("Octavia E. Butler","Bloodchild",
                "fiction",null);
        Assert.assertEquals(shelf.getBooks().get("fiction").size(),2);
    }

    @Test
    public void testInputBook(){
        //figure out if it's possible to test this
        String author = "N.K Jemisin";
        String title = "The Fifth Season";
        Integer type = 1;
        char series = 'y';
        String seriesTitle = "The Broken Earth Triology";
        int vol = 1;

//        String input = "N.K "
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