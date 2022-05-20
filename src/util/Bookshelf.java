package util;

import java.util.ArrayList;
import java.util.Objects;

/**
 * perhaps this should be static and/or implement singleton so can be accessed across multiple classes
 * and only one created
 *
 * should i have multiple shelves (fiction, non-fiction, comics); maybe represent in priority queue?
 */

public class Bookshelf {

    private ArrayList<Book> books;
    private Bookshelf(){
        books = new ArrayList<>();
    }
    private static Bookshelf shelf = new Bookshelf();
    public static Bookshelf getShelf(){
        return shelf;
    }

    //think about implementing a hashcode for the shelf based on title, author, and volume
    /*public int hashCode(Book book){

    }*/

    public void addBook(Book book){
        if (!inShelf(book)) {
            this.books.add(book);
        }

    }

    public boolean inShelf(Book book){
        if (!this.books.isEmpty()) {
            for (Book i : this.books) {
                if (i.equals(book)) {
                    return true;
                }
            }

        }

        return false;
    }

}
