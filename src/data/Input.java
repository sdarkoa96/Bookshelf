package data;

import util.Bookshelf;

/**
 * creates a book based on user input
 *
 * perhaps this should just have a static method that creates a new book?
 *
 * or maybe it should just be the contructor that has things needed to create books then call method to create it
 *
 */
public class Input {

    Bookshelf shelf = Bookshelf.getShelf();
    public Input(Bookshelf shelf){

    }

    public void newBook(String author, String title, String genre, String type, String seriesTitle){

    }

}
