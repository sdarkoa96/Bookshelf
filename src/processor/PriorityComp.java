package processor;

import util.Book;

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
