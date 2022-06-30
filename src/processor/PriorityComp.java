package processor;

import util.Book;

public class PriorityComp implements BookComparator{
    @Override
    public <E> boolean eqBook(Book book1, E o) {
        int priority = (int) o;
        return book1.getPriority() == priority;
    }
}
