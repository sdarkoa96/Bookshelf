package util;

import java.util.*;

/**
 *Represents a bookshelf of books that you've bought and/or plan to buy
 */

public class Bookshelf {

    /**
     * represents bookshelf
     */
    private Map<String, List<Book>> books;

    /**
     * private construction to make sure only one bookshelf created
     */
    private static final Bookshelf shelf = new Bookshelf();

    /**
     * constructs individual shelves representing the 3 types: fiction, non-fiction, comic
     */
    private Bookshelf(){
        books = new TreeMap<>();
        books.put("fiction", new ArrayList<Book>()); //fiction shelf
        books.put("non-fiction", new ArrayList<Book>()); //non-fiction shelf
        books.put("comic", new ArrayList<Book>()); //comic shelf
    }

    /**
     *
     * @return only instance of Bookshelf created in the program
     */
    public static Bookshelf getShelf(){
        return shelf;
    }

    /**
     * @return Map object that represents Bookshelf
     */
    public Map<String, List<Book>> getBooks() {
        return books;
    }

    /**
     * checks if book is in shelf and then adds
     * @param book Book object
     * @return true if book is added to shelf or false if not
     */
    public boolean addBook(Book book){
        if (!inShelf(book)) {
            String type = book.getType(); //get book type
            this.books.get(type).add(book); //add book to respective shelf
            return true;
        }
        return false;

    }

    /**
     * checks if book is in shelf
     * @param book Book object
     * @return true if book is in respective shelf or false if it is not
     * @throws NullPointerException
     */
    public boolean inShelf(Book book) throws NullPointerException{
        //throws null pointer if type not a key in shelf
        String type = book.getType();
        List<Book> typeShelf = this.books.get(type.toLowerCase());
        if (!typeShelf.isEmpty()) {
            return this.books.get(type).contains(book); //checks if book on shelf
        }
        return false;
    }

    /**
     * creates string representation of given shelf
     * @param type fiction, non-fiction, comic
     */
    public void toString(String type){
        List<Book> typeShelf = shelf.getBooks().get(type);
        System.out.println(type.toUpperCase()+": ");
        for (Book i: typeShelf) {
            System.out.println(i.toString());
        }
    }

}
