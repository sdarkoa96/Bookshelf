package processor;

import org.junit.Before;
import org.junit.Test;
import util.Book;

import static org.junit.Assert.*;

public class CompTest {
    AuthorComp author = new AuthorComp();
    PriorityComp priority = new PriorityComp();
    SeriesComp series = new SeriesComp();
    TitleComp title = new TitleComp();

    Book book1 = new Book("Bell Hooks", "all about love","non-fiction",null);
    Book book2 = new Book("Tite Kubo","Zombie Powder","comic","Zombie Powder");
    Book book3 = new Book("N.K. Jemisin","Stone Sky","fiction","Shattered Earth");
    Book book4 = new Book("Tite Kubo","Bleach","comic","Bleach");

    @Before
    public void setUp(){
        this.book1.setPriority(2);
        this.book2.setPriority(1);
        this.book3.setPriority(3);
        this.book4.setPriority(2);
    }

    @Test
    public void authorCompTest(){
        assertTrue(author.eqBook(book1,"bell Hooks"));
        assertTrue(author.eqBook(book2,"Tite Kubo"));
        assertTrue(author.eqBook(book3,"n.k. jemisin"));

    }

    @Test
    public void priorityCompTest(){
        assertTrue(priority.eqBook(book3,3));
        assertTrue(priority.eqBook(book1,2));
        assertTrue(priority.eqBook(book4,2));
    }

    @Test
    public void seriesCompTest(){
        assertFalse(series.eqBook(book2,null));
        assertTrue(series.eqBook(book1,null));
        assertTrue(series.eqBook(book3,"Shattered Earth"));

    }

    @Test
    public void titleCompTest(){
        assertTrue(title.eqBook(book1,"about"));
        assertTrue(title.eqBook(book1,"all about"));
        assertTrue(title.eqBook(book1,"all about love"));
        assertFalse(title.eqBook(book1,"change"));

        //figure out regex for "look for this word EXACTLY and ignore case"
//        assertFalse(title.eqBook(book1,"bout"));
    }

}