package util;

import java.util.*;

/**
 * perhaps this should be static and/or implement singleton so can be accessed across multiple classes
 * and only one created
 *
 * should i have multiple shelves (fiction, non-fiction, comics); maybe represent in priority queue?
 */

public class Bookshelf {

    private Map<String, List<Book>> books;
    private Bookshelf(){
        books = new TreeMap<>();
        books.put("fiction", new ArrayList<Book>());
        books.put("non-fiction", new ArrayList<Book>());
        books.put("comic", new ArrayList<Book>());
    }
    private static Bookshelf shelf = new Bookshelf();
    public static Bookshelf getShelf(){
        return shelf;
    }

    //think about implementing a hashcode for the shelf based on title, author, and volume
    /*public int hashCode(Book book){

    }*/

    public boolean addBook(Book book){
        if (!inShelf(book)) {
            String type = book.getType();
            this.books.get(type).add(book);
            return true;
        }
        return false;

    }

    public Map<String, List<Book>> getBooks() {
        return books;
    }

    public boolean inShelf(Book book) throws NullPointerException{
        //throws null pointer if type not a key in shelf
        String type = book.getType();
        List<Book> typeShelf = this.books.get(type.toLowerCase());
        if (!typeShelf.isEmpty()) {
            return this.books.get(type).contains(book);
        }
        return false;
    }

}
