package processor;

import util.Book;
import util.Bookshelf;
import logging.Logger;

import java.util.*;


public class Update {

    Logger l;
    Bookshelf shelf;
    Set<String> purchased = new TreeSet<>();

    Set<String> titleNotPurchased = new TreeSet<>();

    List<Book> notPurchased = new ArrayList<>(); //might want to make this a list of books so can sort by priority

    public Update(Logger l,Bookshelf shelf){
        this.l = l;
        this.shelf = shelf;
        purchasedStatus(); //populates purchased, titleNotPurchased, and notPurchased fields
    }

    public Set<String> getPurchased() {
        return this.purchased;
    }

    public List<Book> getNotPurchased() {
        return this.notPurchased;
    }

    public Set<String> getTitleNotPurchased() {
        return this.titleNotPurchased;
    }


    /**
     * sort non purchased set by priorty of each book
     */
    public void sortNotPurchased(){
        Comparator<Book> prioritySorter = (Book::compareTo);
        this.notPurchased.sort(prioritySorter);

    }

    /**
     * locates books in bookshelf that match the given parameters
     *
     * @param title book title to find
     * @param author author related to book to find
     * @param type shelf to look in
     * @param seriesTitle series book should be a part of if the book is a part of series
     * @param vol volume number
     */
    public List<Book> findBook(String title, String author, String type,String seriesTitle, int vol){

        if(title == null && vol == 0 && seriesTitle == null || type == null || type.isBlank()){
            System.out.println("Must enter a title and book type (fiction, non-fiction, comic)");
            return null;
        }else if(!(type.equalsIgnoreCase("fiction")||type.equalsIgnoreCase("comic")||type.equalsIgnoreCase("non-fiction"))){
            System.out.println("Must enter a title and book type (fiction, non-fiction, comic)");
            return null;
        }


        assert title != null;
        title = title.toLowerCase();
        List<Book> typeShelf = shelf.getBooks().get(type.toLowerCase());
        List<Book> found = new ArrayList<>();
        String iTitle = null;
        String iAuthor = null;

        if(author == null){ //this will return all books with the same title if you don't know author
            for (Book i: typeShelf){
                iTitle = i.getTitle().toLowerCase();
                if (iTitle.equals(title)){
                    found.add(i);
                }
            }
        } else{ //this is more specific because know the author
            for (Book i: typeShelf){

                iTitle = i.getTitle();
                iAuthor = i.getAuthor();

//                System.out.println(i.getTitle()+" : "+iTitle+" "+i.getAuthor()+" : "+iAuthor+" ");

                if(seriesTitle == null){
                    if(iTitle.equalsIgnoreCase(title) && iAuthor.equalsIgnoreCase(author)){
                        if(vol <= 0){
                            found.add(i);
                        } else if (i.getSeriesVol() == vol) {
                            found.add(i);
                        }
                    }

                }else if(vol == 0 && iAuthor.equalsIgnoreCase(author) && Objects.equals(seriesTitle.toLowerCase(),i.getSeriesTitle().toLowerCase())){
                    found.add(i);
                }
                else if (iTitle.equalsIgnoreCase(title) && iAuthor.equalsIgnoreCase(author) && Objects.equals(seriesTitle.toLowerCase(),i.getSeriesTitle().toLowerCase())){
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
     * switches the status of given book's variable
     * @param bought if null leave book's purchase status alone, if ture set purchase status to true, and if false set purchase status to false
     * @param book Book object
     * @param priority if null make no changes to book's priority variable, if 1 (high priority), 2 (med priority), or 3 (low priority) change book priority
     * @param rem if set to true will trigger condition that removes book from shelf
     */

    public void swapStatus(Boolean bought, Book book, Integer priority, boolean rem){
        if(bought == null && priority != null){
            if(!book.isPurchased()){
                book.setPriority(priority);
                l.logEvent(book+" priority set to: "+priority);
                sortNotPurchased();
            }
        }else if(Boolean.TRUE.equals(bought) && priority == null){
            book.setPurchased(true);
            l.logEvent(book+" set to: purchased");
            this.purchased.add(book.toString());
            this.titleNotPurchased.remove(book.toString());
        }else if(Boolean.FALSE.equals(bought) && priority == null){
            book.setPurchased(false);
            l.logEvent(book+" set to: not purchased");
            this.purchased.remove(book.toString());
            this.titleNotPurchased.add(book.toString());
        }else if(rem){
            this.shelf.getBooks().get(book.getType()).remove(book);
            this.purchased.remove(book.toString());
            this.titleNotPurchased.remove(book.toString());
            l.logEvent(book+" removed from book shelf");
        }
    }

    /**
     * Finds and updates the status of book based on given arguments
     * @param scan gets user input
     * @param bought if null do not want to change purchase status, if false change purchase status to indicate book wasn't bought, if true
     *               book has been purchased
     * @param priority if null do not want to change priority status, if 1 (high priority), 2 (med priority), or 3 (low priority) change book priority
     *                 status
     * @param title title of book to update/change
     * @param author author of book
     * @param type fiction, non-fiction, or comic book
     * @param seriesTitle series name of book
     * @param vol if > 0 the represents the volume of book in a series, if 0 and series title is given updates all volumes of series, if -1
     *            and series title given will allow user to select multiple volumes for status update
     */
    public void updateStatus(Scanner scan, Boolean bought, Integer priority,String title, String author, String type, String seriesTitle, int vol, boolean rem) {
        List<Book> found = findBook(title, author, type, seriesTitle, vol);
        if (found == null || found.size() == 0) {
            System.out.println("Sorry, this book is not in your shelf");

        } else if (found.size() == 1) {
            Book selection = found.get(0);
            swapStatus(bought, selection, priority, rem);

        } else {

            if (seriesTitle == null && vol == 0) {
                //find the single book
                //swapStatus takes care of not entered author

                int number = 1;
                for (Book i : found) {
                    System.out.println(number + ": " + i);
                }

                System.out.println("Enter number to select book or enter -1 to exit: ");
                int index = -1;
                while (true) {
                    try {
                        index = scan.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Enter number to select book or enter -1 to exit: ");
                    }
                }

                if (index > found.size()) {
                    System.out.println("This number is not in the given list");
                } else {
                    Book book = found.get(index - 1);
                    swapStatus(bought, book, priority, rem);
                }

            } else if (seriesTitle != null && vol == -1) {
                //allow user to pick multiple books
                int number = 1;
                for (Book i : found) {
                    System.out.println(number + ": " + i);
                    number++;
                }


                int index = 0;
                while (true) {
                    try {
                        System.out.println("Enter number to select book or enter -1 to exit: ");
                        index = scan.nextInt();
                        if (index <= -1) {
                            break;
                        } else if (index > found.size()) {
                            System.out.println("This number is not in the given list");
                            return;
                        } else {
                            Book book = found.get(index - 1);
                            swapStatus(bought, book, priority, rem);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter number to select book or enter -1 to exit: ");
                    }
                }

            } else if (seriesTitle != null && vol == 0) {
                //change all books in series
                if (author != null) {
                    for (Book i : found) {
                        swapStatus(bought, i, priority, rem);;
                    }
                } else {
                    //print authors
                    ArrayList<String> authorsFound = new ArrayList<>();
                    for (Book i : found) {
                        if (!authorsFound.contains(i.getAuthor())) {
                            authorsFound.add(i.getAuthor());
                        }
                    }

                    int number = 1;
                    for (String i : authorsFound) { //tree set so order will remain the same
                        System.out.println(number + i);
                        number++;
                    }
                    //have user choose author
                    System.out.println("Enter number to select author: ");
                    int index = -1;
                    while (true) {
                        try {
                            index = scan.nextInt();
                            if (index > 0 && index <= authorsFound.size()) {
                                break;
                            } else {
                                System.out.println("You have not selected a number from the given list. Please select a number: ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Enter an integer");
                        }
                    }

                    //select all books of author
                    String selection = authorsFound.get(index - 1);
                    for (Book i : found) {
                        if (selection.equalsIgnoreCase(i.getAuthor())) {
                            swapStatus(bought, i, priority, rem);
                        }
                    }

                }

            }
        }

    }


    /**
     * populates sets of purchased and not purchased books
     */
    public void purchasedStatus(){
        for(Map.Entry<String,List<Book>> pair: shelf.getBooks().entrySet()){
            for(Book i: pair.getValue()){
                String bookInfo = null;

                if(i.isPurchased() && !this.purchased.contains(i.toString())){
                    bookInfo = i.toString();
                    this.purchased.add(bookInfo);
                }else if (!i.isPurchased() && !this.notPurchased.contains(i)){
                    this.notPurchased.add((i));
                }
            }

        }
        sortNotPurchased();

        for(Book i: this.notPurchased){
            String bookInfo = i.toString();
            this.titleNotPurchased.add(bookInfo);
        }
    }


}
