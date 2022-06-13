package processor;

import util.Book;
import util.Bookshelf;

import java.util.*;


public class Purchase{
    Bookshelf shelf = Bookshelf.getShelf();
    List<String> purchased = new ArrayList<>();

    List<String> titleNotPurchased = new ArrayList<>();

    List<Book> notPurchased = new ArrayList<>(); //might want to make this a list of books so can sort by priority

    public Purchase(){
        purchasedStatus();
    }

    public List<String> getPurchased() {
        return purchased;
    }

    public List<Book> getNotPurchased() {
        return notPurchased;
    }

    public List<String> getTitleNotPurchased() {
        return titleNotPurchased;
    }

    public void sortNotPurchased(){
        Comparator<Book> prioritySorter = (Book::compareTo);
        this.notPurchased.sort(prioritySorter);

    }

    /**
     * theoretically should use in update methods to locate a book
     * @param title
     * @param author
     * @param type
     */
    public void findBook(String title, String author, String type){
        if(title == null || type == null || type.isBlank()){
            System.out.println("Must enter a title and book type (fiction, non-fiction, comic");
            return;
//            System.exit(0);
        }

        title = title.toLowerCase();
        type = type.toLowerCase();

    }

    /**
     * Updates whether a book has been purchased or not. Adds and Removes book from appropiate list
     * @param title: title of the book
     * @param author: author's name
     * @param type: ficion, non-fiction, comic
     */
    public void updatePurchaseStatus(String title, String author, String type){

    }

    /**
     * Updates the priority of books to be purchased
     * @param title
     * @param author
     * @param type
     */
    public void updatePriorityStatus(String title, String author, String type){

    }

    public String bookString(Book book){
        StringBuilder bookInfo = new StringBuilder();
        bookInfo.append(book.getTitle());
        if(book.getSeries()){
            bookInfo.append("Vol: ").append(book.getSeriesVol());
        }
        bookInfo.append(book.getAuthor());
        return bookInfo.toString();
    }

    public void purchasedStatus(){
        for(Map.Entry<String,List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                String bookInfo = null;
                //TODO: consider making a string of title, volume, and author to put in lists
                if(i.isPurchased() && !purchased.contains(bookString(i))){
                    bookInfo = bookString(i);
                    purchased.add(bookInfo.toString());
                }else if (!i.isPurchased() && !notPurchased.contains(i)){
                    notPurchased.add((i));
                }
            }

        }
        sortNotPurchased();

        for(Book i: this.notPurchased){
            String bookInfo = bookString(i);
            titleNotPurchased.add(bookInfo);
        }
    }


}
