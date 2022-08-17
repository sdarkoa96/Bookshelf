package processor;

import util.Book;

/**
 * Comparator that checks if book priority matches given priority
 */
public class PriorityComp implements BookComparator{
    @Override
    public <E> boolean eqBook(Book book1, E o) {
        if(book1 == null){
            return false;
        }

        int priority = -1;

        try {
            priority = (int) o;
        }catch (Exception e){
            return false;
        }

        return book1.getPriority() == priority;
    }
}
