package processor;

import org.junit.Before;
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

}