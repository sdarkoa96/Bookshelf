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


    public Pull(){
    }

    public void pullBooks(BookComparator comp, E pullFactor){
        System.out.println("Here are your books: ");
        for(Map.Entry<String, List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                if(comp.eqBook(i,pullFactor)){
                    System.out.println(i.toString());
                }
            }
        }

    }

    public void pullShelf(String type){
        System.out.println("Here are your books: ");
        shelf.toString(type);

    }
}
