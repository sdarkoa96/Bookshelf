package util;

import java.util.ArrayList;
import java.util.Objects;

/**
 * perhaps this should be static and/or implement singleton so can be accessed across multiple classes
 * and only one created
 */

public class Bookshelf {

    private static ArrayList<Book> shelf = new ArrayList<>();
    private Bookshelf(){}
    public static ArrayList<Book> getShelf(){return shelf;}

    //think about implementing a hashcode for the shelf based on title, author, and volume
    /*public int hashCode(Book book){

    }*/

    public void addBook(Book book){
        if (!inShelf(book)) {
            shelf.add(book);
        }

    }

    public boolean inShelf(Book book){
        if (!shelf.isEmpty()) {
            for (Book i : shelf) {
                if (i.equals(book)) {
                    return true;
                }
            }

        }

        return false;
    }

}
