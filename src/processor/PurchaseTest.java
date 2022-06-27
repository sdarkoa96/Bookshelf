package processor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Book;
import util.Bookshelf;

import java.util.List;
import java.util.Set;

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
        book2.setPurchased(false);
        book2.setPriority(2);
        Book book3 = new Book("Mikki Kendall","Hood Feminism","non-fiction",null);
        book3.setPurchased(false);
        book3.setPriority(1);
        Book book4 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book4.setSeriesVol(2);
        book4.setPurchased(true);
        Book book5 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book5.setSeriesVol(22);
        book5.setPurchased(false);
        book5.setPriority(3);

        shelf.addBook(book1);
        shelf.addBook(book2);
        shelf.addBook(book3);
        shelf.addBook(book4);
        shelf.addBook(book5);
    }

    @Test
    public void sortNotPurchased() {
        Purchase test = new Purchase();
        test.sortNotPurchased();
        List<Book> testLst = test.getNotPurchased();
        Assert.assertEquals(testLst.get(0).getTitle(),"Hood Feminism");
        Assert.assertEquals(testLst.get(1).getTitle(),"The Obelisk Gate");
        Assert.assertEquals(testLst.get(2).getTitle(),"Bleach");
    }

    @Test
    public void findBook() {
        Purchase test = new Purchase();

        //null title
        List<Book> test1 = test.findBook(null,"Silivia Morena","fiction",null,0);
        Assert.assertEquals(null,test1);

        //null author
        List<Book> test2 = test.findBook("Bleach",null,"comic",null,0);
        Assert.assertEquals(test2.size(),2);

        //empty author
        List<Book> test2 = test.findBook("Bleach","","comic",null,0);
        Assert.assertTrue(test2.isEmpty());

        //null type
        List<Book> test3 = test.findBook("Bleach","Tite Kubo",null,"Bleach",0);
        Assert.assertNull(test3);

        //empty type
        test3 = test.findBook("Bleach","Tite Kubo","","Bleach",0);
        Assert.assertNull(test3);

        //wrong type
        test3 = test.findBook("Bleach","Tite Kubo","newspaper","Bleach",0);
        Assert.assertNull(test3);


    }

    @Test
    public void swapStatus() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void purchasedStatus() {
        Purchase test = new Purchase();
        test.sortNotPurchased();
        List<Book> testLst1 = test.getNotPurchased();
        Set<String> testLst2 = test.getPurchased();
        Assert.assertFalse(testLst1.isEmpty());
        Assert.assertFalse(testLst2.isEmpty());

    }
}