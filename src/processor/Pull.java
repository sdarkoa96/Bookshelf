package processor;

import util.Book;
import util.Bookshelf;

import java.util.List;
import java.util.Map;

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
        System.out.println("Here are your books: ");
        for(Map.Entry<String, List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                if(this.comp.eqBook(i,pullFactor)){
                    System.out.println(i.toString());
                }
            }
        }

    }

    public void pullShelf(String type){
        shelf.toString(type);

    }
}
