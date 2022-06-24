package processor;

import org.junit.Before;
import org.junit.Test;
import util.Book;
import util.Bookshelf;

import static org.junit.Assert.*;

public class PurchaseTest {
    Bookshelf shelf = Bookshelf.getShelf();

    @Before
    public void setUp() throws Exception {
        Book book1 = new Book("NK Jemisin","The Fifth Season","fiction","The Broken Earth Trilogy");
        book1.setSeriesVol(1);
        book1.setPurchased(true);
        Book book2 = new Book("NK Jemisin","The Obelisk Gate","fiction","The Broken Earth Trilogy");
        book2.setSeriesVol(2);
        Book book3 = new Book("Mikki Kendall","Hood Feminism","non-fiction",null);
        Book book4 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book4.setSeriesVol(2);
        book4.setPurchased(true);
        Book book5 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book5.setSeriesVol(22);
        book5.setPurchased(false);

    }

    @Test
    public void sortNotPurchased() {
    }

    @Test
    public void findBook() {
    }

    @Test
    public void swapStatus() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void purchasedStatus() {
    }
}