package util;

import org.junit.Test;
import org.testng.Assert;

public class BookshelfTest {
    Bookshelf shelf = Bookshelf.getShelf();

    @Test
    public void addBook() {
    }

    @Test
    public void inShelf() {
        Book newBook = new Book("Octavia E. Butler","Kindred",
                "fiction",null);
        Assert.assertFalse(shelf.inShelf(newBook));
    }
}