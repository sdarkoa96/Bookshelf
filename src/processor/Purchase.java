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



    public void purchasedStatus(){
        for(Map.Entry<String,List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                //TODO: consider making a string of title, volume, and author to put in lists
                if(i.isPurchased() && !purchased.contains(i)){
                    purchased.add(i.getTitle());
                }else if (!i.isPurchased() && !notPurchased.contains(i)){
                    notPurchased.add((i));
                }
            }

        }
        sortNotPurchased();

        for(Book i: this.notPurchased){
            String
        }
    }


}
