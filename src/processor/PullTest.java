package processor;

import org.junit.Before;
import org.junit.Test;
import util.Book;
import util.Bookshelf;

public class PullTest {

    Bookshelf shelf = Bookshelf.getShelf();
    Book book1 = new Book("bell hooks","all about love","non-fiction",null);
    Book book2 = new Book("Rivers Solomon","Sorrowland","fiction", null);
    Book book3 = new Book("Sarah Vaughn", "Sleepless", "comic", "Sleepless");
    Book book4 = new Book("Marissa Meyer", "Winter","fiction","The Lunar Chronicles");
    Book book5 = new Book("Marissa Meyer", "Cress","fiction","The Lunar Chronicles");

    AuthorComp author = new AuthorComp();
    PriorityComp priority = new PriorityComp();
    SeriesComp series = new SeriesComp();
    TitleComp title = new TitleComp();

    @Before
    public void setUp(){
        book1.setPriority(1);
        book2.setPriority(2);
        book3.setPriority(3);
        book3.setSeriesVol(2);
        book4.setSeriesVol(4);
        book4.setPriority(2);
        book5.setSeriesVol(3);
        book5.setPriority(1);
        shelf.addBook(book3);
        shelf.addBook(book4);
        shelf.addBook(book1);
        shelf.addBook(book5);
        shelf.addBook(book2);
    }

    @Test
    public void pullBooks() {
        Pull test = new Pull();
        test.pullBooks(this.author, "marissa meyer");
        test = new Pull();
        test.pullBooks(this.series, "Sleepless");
        test = new Pull();
        test.pullBooks(this.priority, 2);
        test = new Pull();
        test.pullBooks(this.title, "Sorrowland");

        System.out.println();
        System.out.println("Egde Cases");
        test = new Pull();
        test.pullBooks(null, "Sorrowland");
        test = new Pull();
        test.pullBooks(this.priority, (Integer) null);
        test = new Pull();
        test.pullBooks(this.title, (String) null);
        test = new Pull();
        test.pullBooks(this.title, "");
    }

    @Test
    public void pullShelf() {
        Pull test = new Pull();
        test.pullShelf("fiction");
        test = new Pull();
        test.pullShelf("");
    }
}