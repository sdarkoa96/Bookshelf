package processor;

import util.Book;

/**
 * Comparator that checks if book author matches given author
 */
public class AuthorComp implements BookComparator{

    public AuthorComp(){};

    @Override
    public <E> boolean eqBook(Book book1, E author) {
        if(book1 == null || author == null){
            return false;
        }

        String authorStr = (String) author;

        return book1.getAuthor().equalsIgnoreCase(authorStr.toLowerCase());
    }
}
