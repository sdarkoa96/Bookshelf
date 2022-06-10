package data;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.Bookshelf;

class InputTest {

    @Test
    public void testNewBook(){
        Bookshelf shelf = Bookshelf.getShelf();
        Input testIn = new Input();
        testIn.newBook("Octavia E. Butler","Kindred",
                "ficion",null);
        Assert.assertFalse(shelf.getBooks().isEmpty());
    }

}