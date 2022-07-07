package ui;

import data.CSVReader;
import data.Input;
import processor.*;
import util.Bookshelf;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Output {
    CSVReader csv = null;
    Input input = null;
    Pull pull = null;
    Update update = null;

    AuthorComp authorComp = new AuthorComp();
    PriorityComp priorityComp = new PriorityComp();
    SeriesComp seriesComp = new SeriesComp();
    TitleComp titleComp = new TitleComp();



    Bookshelf shelf = Bookshelf.getShelf();


    public Output(CSVReader csv, Input input, Pull pull, Update update){
        this.csv = csv;
        this.input = input;
        this.pull = pull;
        this.update = update;

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
        System.out.println("13. Show shelf");
        System.out.println("14. Exit");
        System.out.println();
    }

    public void pull(int option){
        Scanner pullOption = new Scanner(System.in);
        switch (option){
            case 3:
                System.out.println("Enter your author: ");
                String author = pullOption.next();
                pull.pullBooks(authorComp,author);
            case 4:
                System.out.println("Enter your book title: ");
                String title = pullOption.next();
                pull.pullBooks(titleComp,title);
            case 5:

                int priority = -1;
                while (priority>3 || priority<1){
                    System.out.println("Enter your priority level of choice (1-3): ");
                    try{
                        priority = pullOption.nextInt();
                    }catch (InputMismatchException e){
                        continue;
                    }
                }
                pull.pullBooks(priorityComp,priority);
            case 6:
                System.out.println("Enter your series title: ");
                String series = pullOption.next();
                pull.pullBooks(seriesComp,series);

            case 7:
                String[] types = {"fiction","non-fiction","comic"};
                System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
                int typeInd = -1;
                while (true){
                    typeInd = pullOption.nextInt();
                    try{
                        pull.pullShelf(types[typeInd-1]);
                        break;
                    }catch (Exception e){
                        System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
                    }
                }

        }
    }

    public void execute() throws IOException {
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

            while (option > 14 || option < 1){
                System.out.println("Please enter a valid option.");
                option = scan.nextInt();
            }

            switch (option){
                case 1: input();
                case 2: csv();
                case 14: break;
            }

        }
        scan.close();
    }

    public void input(){
        Scanner inputScan = new Scanner(System.in);
        this.input.inputBook(inputScan);
    }

    public void csv() {
        Scanner csvScan = new Scanner(System.in);
        System.out.println("Enter your filename/path: ");
        String filename = csvScan.next();
        try {
            this.csv.readBook(filename);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
