package processor;

import util.Book;

public class AuthorComp implements BookComparator{

    public AuthorComp(){};

    @Override
    public boolean eqBook(Book book1, Book book2) {

        return book1.getAuthor().equalsIgnoreCase(book2.getAuthor());
    }
}
