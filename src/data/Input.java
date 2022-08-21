package data;

import logging.Logger;
import util.Book;
import util.Bookshelf;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates a Book object based on data gathered from user input
 */
public class Input {

    /**
     * logs whether book is added into the shelf
     */
    Logger logger;
    /**
     * holds Book objects in lists representing shelves: fiction, non-fiction, and comic
     */
    Bookshelf shelf;

    public Input(Logger logger, Bookshelf shelf){
        this.logger = logger;
        this.shelf = shelf;
    }

    /**
     * uses scanner to gather user input to populate fields of Book object
     * @param scan scanner to get user input
     * @throws Exception
     */
    public void inputData(Scanner scan)throws Exception{

        System.out.println("How many books would you like to enter?");
        int loops = -1;
        do {
            try {
                loops = scan.nextInt(); //determines how many Book objects user will create
            } catch (Exception e) {
                System.out.println("Please enter a positive number: ");
            }
            scan = new Scanner(System.in);
        } while (loops <= -1); //condition for executing do block

        int count = 0; //loop control

        while (count != loops){

            //get author
            String author = null;
            System.out.println("Enter the book author: ");
            while (author == null || author.isBlank()) {
                author = scan.next();
            }

            //get title
            scan = new Scanner(System.in);
            String title = null;
            System.out.println("Enter the book title: ");
            while(title == null || title.isBlank()){
                title = scan.nextLine();
            }


            //select book type
            scan = new Scanner(System.in);
            String type = null;
            int typeBook = 0;
            System.out.println("Enter 1 for fiction, 2 for non-fiction, or 3 for a comic: ");
            while(true){
                try {
                    typeBook = scan.nextInt();
                    switch (typeBook){
                        case 1:
                            type = "fiction";
                        case 2:
                            type = "non-fiction";
                        case 3:
                            type = "comic";
                    }
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Enter 1 for fiction, 2 for non-fiction, or 3 for a comic: ");
                }
            }

            //get series and volume information
            String seriesTitle = null;
            int seriesVol = 0;
            System.out.println("Is this book a part of a series Y/N?");

            String ans = scan.next();
            if(ans.length() == 1){
                char answer = ans.charAt(0);
                if (answer == 'y' || answer == 'Y'){
                    System.out.println("Enter series title: ");
                    seriesTitle = scan.next();
                    scan = new Scanner(System.in);

                    while(true){
                        System.out.println("Enter a the volume number.");
                        try{
                            seriesVol = scan.nextInt();
                            break;
                        }catch (InputMismatchException ignored){
                        }
                    }
                }
            }

            //creates Book
            Book book = newBook(author,title, type,seriesTitle);

            //set series volume
            if(seriesTitle != null){
                book.setSeriesVol(seriesVol);
            }

            //set purchase status of book
            scan = new Scanner(System.in);
            System.out.println("Have you purchased this book Y/N?");
            ans = scan.next();
            book.setPurchased(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes"));

            //set priority of book
            scan = new Scanner(System.in);
            if(!book.isPurchased()){
                System.out.println("Would you like to set the priority level of this book");
                ans = scan.next();
                if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("y")) {
                    while (true) {
                        scan = new Scanner(System.in);
                        System.out.println("Enter 1 (high priority), 2 (med priority), or 3 (low priority): ");
                        try {
                            int answer = scan.nextInt();
                            if (answer > 0 && answer < 4) {
                                book.setPriority(answer);
                                break;
                            }
                        } catch (InputMismatchException ignored) {

                        }
                    }
                }
                else {
                    book.setPriority(-1);
                }
            }else {
                book.setPriority(-1);
            }

            count ++;
        }

    }

    /**
     * creates new book, adds it to shelf and logs action
     * @param author book author
     * @param title book title
     * @param type fiction, non-fiction, comic
     * @param seriesTitle title of series book belongs to
     * @return Book created
     */
    public Book newBook(String author, String title, String type, String seriesTitle){
        Book newBook = new Book(author, title, type, seriesTitle);
        logger.logEvent(newBook.getTitle() +" added to shelf: "+shelf.addBook(newBook)); //consider using logger for print statement
        return newBook;
    }

}
