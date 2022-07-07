package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Output {

    public Output(){

    }

    public void options(){
        System.out.println("Welcome to your book shelf. Please select an option.");
        System.out.println();
        System.out.println("1. Manually enter book(s) to add to bookshelf"); //call input
        System.out.println("2. Read book(s) from a file to add to bookshelf"); //csvreader
        System.out.println("3. Show all the books by author of choice");
        System.out.println("4. Show all the books by title of choice");
        System.out.println("5. Show all the books by priority of choice");
        System.out.println("6. Show all the books by series of choice");
        System.out.println("7. Show all the books by type of choice");
        System.out.println("8. Remove book(s) from bookshelf");
        System.out.println("9. Update purchase status of book(s) from bookshelf");
        System.out.println("10. Show purchased book(s)"); //getter from update
        System.out.println("11. Show book(s) that have yet to be purchased"); //getter from update
        System.out.println("12. Create bookshelf document"); //flesh out write
        System.out.println("13. Exit");
        System.out.println();
    }

    public void execute(){
        Scanner scan = new Scanner(System.in);
        while (true){
            options();
            int option = -1;

            while (true) {
                try {
                    option = scan.nextInt();
                    break;
                } catch (InputMismatchException | IllegalStateException e) {
                    System.out.println("Please enter a number.");
                }
            }

            while (option > 13 || option < 1){
                System.out.println("Please enter a valid option.");
                option = scan.nextInt();
            }

            case

        }
        scan.close();
    }
}
