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

    public void swapStatus(boolean bought,Book book){
        if(bought){
            book.setPurchased(true);
            purchased.add(book.toString());
            titleNotPurchased.remove(book.toString());
        }else{
            book.setPurchased(false);
            purchased.remove(book.toString());
            titleNotPurchased.add(book.toString());
        }
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

    /*TODO: break this down into 2 methods; one for updating to purchased and another for updating to not purchased
       this will allow us to limit the amount of times need user input to handle multiple books at once
     */

    public void updatePurchaseStatus(Scanner scan, boolean bought, String title, String author, String type,String seriesTitle, int vol){
        List<Book> found = findBook(title,author,type,seriesTitle,vol);

        //assume that if found.size() == 1; we've found the exact book we want to remove and exit method
        if(found == null || found.size() == 0){
            System.out.println("Sorry, this book is not in your shelf");
            return;
        }else if(found.size()==1){
            Book selection = found.get(0);
//            String selected = selection.toString();
            swapStatus(bought,selection);
//            if(!bought){
//                selection.setPurchased(false);
//                //remove from purchased list
//                purchased.remove(selected);
//                titleNotPurchased.add(selected);
//            }else if(bought){
//                selection.setPurchased(true);
//                purchased.add(selected);
//                titleNotPurchased.remove(selected);
//            }
            return;
        }

        //TODO: if author not input, grab list of authors and display them. have user pick which author to remove or exit.
        if (author == null){
            Map<String,Book> selectBook = new TreeMap<>();
            Map<Integer,String> options = new TreeMap<>();
            int number = 1;
            for(Book i: found){
                selectBook.put(i.toString(), i);
                options.put(number,i.toString());
                number ++;
            }

            System.out.println("Here are the list of books with this title: ");
            for(Map.Entry<Integer,String> i: options.entrySet()){
                System.out.println(i.getKey()+": "+i.getValue());
            }

            if(vol == -1){
                System.out.println("Enter number associated with the book you would like to change the purchase status of or enter -1 if finished");
                int choice = scan.nextInt();
                String bookChoice = null;
                Book book = null;
                while (choice != -1){
                    if(options.containsKey(choice)){
                        bookChoice = options.get(choice);
                        book = selectBook.get(bookChoice);
                        swapStatus(bought,book);
//                        if(bought){
//                            book.setPurchased(true);
//                            purchased.add(book.toString());
//                            titleNotPurchased.remove(book.toString());
//                        }else{
//                            book.setPurchased(false);
//                            purchased.remove(book.toString());
//                            titleNotPurchased.add(book.toString());
//                        }
                    }else {
                        System.out.print("Incorrect entry.");
                    }
                    System.out.println("Enter number associated with the book you would like to change the purchase status of or enter -1 if finished");
                    choice = scan.nextInt();
                }
                return;
            }else if(vol == 0 ){
                System.out.println("Enter number associated with the book you would like to change the purchase status of: ");
                int choice = scan.nextInt();
                String bookChoice = null;
                Book book = null;

                if(options.containsKey(choice)){
                    bookChoice = options.get(choice);
                    book = selectBook.get(bookChoice);
                    swapStatus(bought,book);
                }else {
                    System.out.print("Incorrect entry.");
                }

                return;
            }

        }

        //TODO: if series entered but vol == 0, remove and entire series
        else if(seriesTitle != null){
            List<Book> sorted = new ArrayList<>(found); //sort by volume
            if(vol == 0) {
                for (Book i : found) {
                    swapStatus(bought, i);
                }
                return;
            }else if(vol == -1){

                System.out.println("Enter volume you would like to change the purchase status of or enter -1 if finished");
                int choice = scan.nextInt();
                while (choice != -1){
                    //pull book using index
                    try {
                        swapStatus(bought, sorted.get(choice - 1));
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("This volume is not in your shelf.");
                    }

                    System.out.println("Enter volume you would like to change the purchase status of or enter -1 if finished");
                    choice = scan.nextInt();
                }
                return;
            }else {
                System.out.println("Enter volume you would like to change the purchase status of");
                int choice = scan.nextInt();
                try {
                    swapStatus(bought, sorted.get(choice - 1));
                }catch (IndexOutOfBoundsException e){
                    System.out.println("This volume is not in your shelf.");
                }
                return;
            }
        }

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
