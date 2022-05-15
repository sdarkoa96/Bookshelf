package util;

import java.util.ArrayList;
import java.util.Objects;

public class Bookshelf {

    ArrayList<Book> shelf = new ArrayList<>();

    public Bookshelf(){

    }

    //think about implementing a hashcode for the shelf based on title, author, and volume
    /*public int hashCode(Book book){

    }*/

    public void addBook(Book book){
        if (!inShelf(book)) {
            this.shelf.add(book);
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
