package processor;

import util.Book;
import util.Bookshelf;

import java.util.List;
import java.util.Map;

/**
 * displays all the books that match with a given input using a comparator
 */
public class Pull{
    Bookshelf shelf;

    public Pull(Bookshelf shelf){
        this.shelf = shelf;
    }

    /**
     * prints books based on comparator evaluation
     * @param comp comparator that will compare pullfactor to corresponding element of Book
     * @param pullFactor value looking for in Book object
     */
    public void pullBooks(BookComparator comp, String pullFactor){
        if(comp == null){
            System.out.println("You've entered an invalid comparator");
        }else if(pullFactor == null || pullFactor.isBlank()){
            System.out.println("You've entered an invalid pull factor");
        }else {
            System.out.println("Here are your books: ");
            for (Map.Entry<String, List<Book>> pair : shelf.getBooks().entrySet()) {
                if(pair.getValue().isEmpty()){
                    continue;
                }
                for (Book i : pair.getValue()) {
                    //uses comparator to check if Book contains pullFactor
                    if (comp.eqBook(i, pullFactor)) {
                        System.out.println(i.toString());
                    }
                }
            }
        }
    }

    /**
     * same logic as above but for priority
     * @param comp comparator that will compare pullfactor to corresponding element of Book
     * @param pullFactor value looking for in Book object
     */
    public void pullBooks(BookComparator comp, Integer pullFactor){
        if(comp == null){
            System.out.println("You've entered an invalid comparator");
        }else if(pullFactor == null){
            System.out.println("You've entered an invalid pull factor");
        }else {
            System.out.println("Here are your books: ");
            for (Map.Entry<String, List<Book>> pair : shelf.getBooks().entrySet()) {
                if(pair.getValue().isEmpty()){
                    continue;
                }
                for (Book i : pair.getValue()) {
                    if (comp.eqBook(i, pullFactor)) {
                        System.out.println(i.toString());
                    }
                }
            }
        }

    }

    /**
     * pulls all the books on the given shelf
     * @param type non-fiction, fiction, comic
     */
    public void pullShelf(String type){
        if( type == null || type.isBlank()){
            System.out.println("You've entered an invalid type");
            return;
        }
        System.out.println("Here are your books: ");
        shelf.toString(type);

    }
}
