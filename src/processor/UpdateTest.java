package processor;

import data.CSVReader;
import logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Book;
import util.Bookshelf;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UpdateTest {
    Bookshelf shelf = Bookshelf.getShelf();
    Logger l = Logger.getInstance();
    Book book1 = null;
    Book book2 = null;
    Book book3 = null;
    Book book4 = null;
    Book book5 = null;
    CSVReader csv = new CSVReader(l,shelf);


    @Before
    public void setUp() throws Exception {
        l.setDestination();
//        csv.readBook("test1.csv");

        book1 = new Book("NK Jemisin","The Fifth Season","fiction","The Broken Earth Trilogy");
        book1.setSeriesVol(1);
        book1.setPurchased(true);
        book2 = new Book("NK Jemisin","The Obelisk Gate","fiction","The Broken Earth Trilogy");
        book2.setSeriesVol(2);
        book2.setPurchased(false);
        book2.setPriority(2);
        book3 = new Book("Mikki Kendall","Hood Feminism","non-fiction",null);
        book3.setPurchased(false);
        book3.setPriority(1);
        book4 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book4.setSeriesVol(2);
        book4.setPurchased(true);
        book5 = new Book("Tite Kubo","Bleach","comic","Bleach");
        book5.setSeriesVol(22);
        book5.setPurchased(false);
        book5.setPriority(3);

        shelf.addBook(book1);
        shelf.addBook(book2);
        shelf.addBook(book3);
        shelf.addBook(book4);
        shelf.addBook(book5);
    }

//    @Test
    public void sortNotPurchased() {
        Update test = new Update(l,shelf);
        test.sortNotPurchased();
        List<Book> testLst = test.getNotPurchased();
        Assert.assertEquals(testLst.get(0).getTitle(),"Hood Feminism");
        Assert.assertEquals(testLst.get(1).getTitle(),"The Obelisk Gate");
        Assert.assertEquals(testLst.get(2).getTitle(),"Bleach");
    }

    @Test
    public void findBook() {
        Update test = new Update(l,shelf);
//        List<Book> testSeries = test.findBook("","N.K. Jemisin","fiction","The Shattered Earth Triology",0);
//        Assert.assertEquals(testSeries.size(),2);


        //all the books in a series without giving a title
        List<Book> testSeries = test.findBook("","NK Jemisin","fiction","The Broken Earth Trilogy",0);
        Assert.assertEquals(testSeries.size(),2);

        //null title
        List<Book> test1 = test.findBook(null,"Silivia Morena","fiction",null,0);
        assertNull(test1);

        //empty title
        test1 = test.findBook("","Tite Kubo","comic","Bleach",4);
        Assert.assertTrue(test1.isEmpty());

        //null author
        List<Book> test2 = test.findBook("Bleach",null,"comic",null,0);
        Assert.assertEquals(test2.size(),2);

        //empty author
        test2 = test.findBook("Bleach","","comic",null,0);
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

        //regular input
        List<Book> test4 = test.findBook("Bleach","Tite kubo","comic","bleach",0);
        Assert.assertEquals(test4.size(),2);

        test4 = test.findBook("Bleach","Tite kubo","comic","bleach",22);
        Assert.assertEquals(test4.size(),1);

    }

//    @Test
    public void swapStatus() {
        Update test = new Update(l,shelf);
        test.swapStatus(true,this.book2,null, false);
        Assert.assertTrue(this.book2.isPurchased());

        test.swapStatus(false,this.book1,null, false);
        Assert.assertFalse(this.book1.isPurchased());

        test.swapStatus(null,this.book3,3, false);
        Assert.assertEquals(this.book3.getPriority(),3);
    }

    @Test
    public void updateStatus() {
        //TODO: figure out how to test with scanner
    }

    @Test
    public void purchasedStatus() {
        Update test = new Update(l,shelf);
        test.sortNotPurchased();
        List<Book> testLst1 = test.getNotPurchased();
        Set<String> testLst2 = test.getPurchased();
        Assert.assertFalse(testLst1.isEmpty());
        Assert.assertFalse(testLst2.isEmpty());
        assertEquals(2, testLst2.size());

    }
}