package data;

import util.Book;
import util.Bookshelf;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * creates a book based on user input
 *
 * perhaps this should just have a static method that creates a new book?
 *
 * or maybe it should just be the contructor that has things needed to create books then call method to create it
 *
 * should I have a local scanner that I create and close in a single method which will call newBook?
 */
public class Input {

    Bookshelf shelf = Bookshelf.getShelf();
    public Input(){


    }

    public void inputBook(Scanner scan){
//        Scanner scan = new Scanner(System.in);

        String author = null;
        System.out.println("Enter the book author: ");
        author = scan.next();

        String title = null;
        System.out.println("Enter the book title: ");
        title = scan.next();

//        String genre = null;
//        System.out.println("Enter the book genre: ");
//        genre = scan.next();

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

        String seriesTitle = null;
        int seriesVol = 0;
        System.out.println("Is this book a part of a series Y/N?");

        String ans = scan.next();
        if(ans.length() == 1){
            char answer = ans.charAt(0);
            if (answer == 'y' || answer == 'Y'){
                System.out.println("Enter series title: ");
                seriesTitle = scan.next();

                while(true){
                    try{
                        seriesVol = scan.nextInt();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Enter a the volume number.");
                    }
                }
            }
        }

        Book book = new Book(author,title, type,seriesTitle);
        if(seriesTitle != null){
            book.setSeriesVol(seriesVol);
        }


        scan.close(); //close scanner
    }

    public void newBook(String author, String title, String type, String seriesTitle){
        Book newBook = new Book(author, title, type, seriesTitle);
        System.out.println("Book added to shelf: "+shelf.addBook(newBook)); //consider using logger for print statement
    }

}
