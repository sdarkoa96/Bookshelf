package processor;

import util.Book;

public interface BookComparator {
    public <E> boolean eqBook(Book book1,E o);
}
