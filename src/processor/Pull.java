package processor;

import util.Book;
import util.Bookshelf;

/**
 * displays all the books that match with a given comparator
 */
public class Pull <E>{
    Bookshelf shelf = Bookshelf.getShelf();
    BookComparator comp = null;
    E pullFactor = null;

    public Pull(BookComparator comp, E pullFactor){
        this.comp = comp;
        this.pullFactor = pullFactor;
    }

    public void pullBooks(){

    }
}
