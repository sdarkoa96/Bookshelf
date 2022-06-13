package processor;

import util.Book;
import util.Bookshelf;

import java.util.*;


public class Purchase{
    Bookshelf shelf = Bookshelf.getShelf();
    Set<String> purchased = new TreeSet<>();

    Set<String> titleNotPurchased = new TreeSet<>();

    List<Book> notPurchased = new ArrayList<>(); //might want to make this a list of books so can sort by priority

    public Purchase(){
        purchasedStatus();
    }

    public Set<String> getPurchased() {
        return purchased;
    }

    public List<Book> getNotPurchased() {
        return notPurchased;
    }

    public Set<String> getTitleNotPurchased() {
        return titleNotPurchased;
    }

    public void sortNotPurchased(){
        Comparator<Book> prioritySorter = (Book::compareTo);
        this.notPurchased.sort(prioritySorter);

    }

    /**
     * theoretically should use in update methods to locate a book. Maybe should return book so it can used to find in list
     *
     * might refactor to be able to remove multiple volumes of series, not just one or all: if series title entered and \
     * vol == -1 could trigger condition that will call for user input to select specific volumes
     * @param title
     * @param author
     * @param type
     * @param seriesTitle if this is null don't look at it
     * @param vol if null set to 0? or don't use?
     */
    public List<Book> findBook(String title, String author, String type,String seriesTitle, int vol){
        if(title == null || type == null || type.isBlank()){
            System.out.println("Must enter a title and book type (fiction, non-fiction, comic");
            return null;
//            System.exit(0);
        }


        title = title.toLowerCase();
        List<Book> typeShelf = shelf.getBooks().get(type.toLowerCase());
        List<Book> found = new ArrayList<>();
        String iTitle = null;
        String iAuthor = null;
        //Todo: think about how want to implement series
        if(author == null){ //this will return all books with the same title if you don't know author
            for (Book i: typeShelf){
                iTitle = i.getTitle().toLowerCase();
                if (iTitle.equals(title)){
                    found.add(i);
                }
            }
        } else{ //this is more specific because know the author
            for (Book i: typeShelf){
                iTitle = i.getTitle().toLowerCase();
                iAuthor = i.getAuthor().toLowerCase();
                if (iTitle.equals(title) && iAuthor.equals(author) && Objects.equals(seriesTitle,i.getSeriesTitle())){
                    if(vol <= 0){
                        found.add(i);
                    } else if (i.getSeriesVol() == vol) {
                        found.add(i);
                    }
                }
            }

        }

        return found;

    }

    /**
     * Updates whether a book has been purchased or not. Adds and Removes book from appropiate list
     *
     * might refactor to be able to remove multiple volumes of series, not just one or all: if series title entered and \
     * vol == -1 could trigger condition that will call for user input to select specific volumes
     * @param title: title of the book
     * @param author: author's name
     * @param type: ficion, non-fiction, comic
     */
    public void updatePurchaseStatus(String title, String author, String type,String seriesTitle, int vol){
        List<Book> found = findBook(title,author,type,seriesTitle,vol);

        //assume that if found.size() == 1; we've found the exact book we want to remove and exit method
        if(found == null){
            System.out.println("Sorry, this book is not in your shelf");
            return;
        }else if(found.size()==1){
            Book selection = found.get(0);
            String selected = bookString(selection);
            if(selection.isPurchased()){
                selection.setPurchased(false);
                //remove from purchased list
                purchased.remove(selected);
                titleNotPurchased.add(selected);
            }else{
                selection.setPurchased(true);
                purchased.add(selected);
                titleNotPurchased.remove(selected);
            }
            return;
        }

        //TODO: if author not input, grab list of authors and display them. have user pick which author to remove or exit.
        if (author == null){}

        //TODO: if series entered but vol == 0, remove and entire series

    }

    /**
     * Updates the priority of books to be purchased
     * @param title
     * @param author
     * @param type
     */
    public void updatePriorityStatus(String title, String author, String type,String seriesTitle, int vol){

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
