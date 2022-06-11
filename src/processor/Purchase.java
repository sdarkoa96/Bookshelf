package processor;

import util.Book;
import util.Bookshelf;

import java.util.*;


public class Purchase{
    Bookshelf shelf = Bookshelf.getShelf();
    List<String> purchased = new ArrayList<>();



    List<Book> notPurchased = new ArrayList<>(); //might want to make this a list of books so can sort by priority

    public Purchase(){

    }

    public List<String> getPurchased() {
        return purchased;
    }

    public List<Book> getNotPurchased() {
        return notPurchased;
    }

    public void sortNotPurchased(){
        Comparator<Book> prioritySorter = ((o1, o2) -> o1.compareTo(o2));
        Collections.sort(this.notPurchased,prioritySorter);

    }



    public void purchasedStatus(){
        for(Map.Entry<String,List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                if(i.isPurchased() && !purchased.contains(i)){
                    purchased.add(i.getTitle());
                }else if (!i.isPurchased() && !notPurchased.contains(i)){
                    notPurchased.add((i));
                }
            }

        }

        sortNotPurchased();
    }


}
