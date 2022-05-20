package process;

import util.Book;
import util.Bookshelf;

import java.util.HashSet;

public class PurchaseStatus {
    public static HashSet<Book> purchaseMemory = new HashSet<>();
    public static HashSet<Book> wishlist = null;
    public PurchaseStatus(){
        Bookshelf shelf = Bookshelf.getShelf();
        wishlist = new HashSet<>(shelf.getBooks());
//        for(Book m: shelf.getBooks()){
//
//        }
    }

    /**
     * sets book status to purchased
     * @param book
     */
    public static void purchased(Book book){
        if(wishlist.contains(book)){
            wishlist.remove(book);
        }

        book.setPurchased(true);
        purchaseMemory.add(book);
    }


}
