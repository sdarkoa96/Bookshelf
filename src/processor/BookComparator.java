package processor;

import util.Book;

/**
 * Compares a field of a Book object to another object
 */
public interface BookComparator {
    public <E> boolean eqBook(Book book1,E o);
}
