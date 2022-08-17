package ui;

import data.CSVReader;
import data.Input;
import logging.Logger;
import processor.*;
import util.Bookshelf;
import write.Writer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Output {

    Scanner scan = null;
    CSVReader csv = null;
    Input input = null;
    Pull pull = null;
    Update update = null;

    Writer writer = null;
    Logger logger = null;

    AuthorComp authorComp = new AuthorComp();
    PriorityComp priorityComp = new PriorityComp();
    SeriesComp seriesComp = new SeriesComp();
    TitleComp titleComp = new TitleComp();
    Bookshelf shelf;


    public Output(CSVReader csv, Input input, Pull pull, Writer write, Bookshelf shelf, Scanner scan, Logger logger){
        this.scan = scan;
        this.csv = csv;
        this.input = input;
        this.pull = pull;
        this.writer = write;
        this.shelf = shelf;
        this.logger = logger;
    }

    public void options(){

        System.out.println();
        System.out.println("1. Manually enter book(s) to add to bookshelf"); //call input
        System.out.println("2. Read book(s) from a file to add to bookshelf"); //csvreader
        System.out.println("3. Show all the books by author of choice");
        System.out.println("4. Show all the books by title of choice");
        System.out.println("5. Show all the books you still need to purchase by your priority level of choice");
        System.out.println("6. Show all the books by series of choice");
        System.out.println("7. Show all the books by type of choice");
        System.out.println("8. Remove book(s) from bookshelf");
        System.out.println("9. Update purchase status of book(s) from bookshelf");
        System.out.println("10. Set the priority level of books you need to purchase");
        System.out.println("11. Show purchased book(s)"); //getter from update
        System.out.println("12. Show book(s) that have yet to be purchased"); //getter from update
        System.out.println("13. Create bookshelf document");
        System.out.println("14. Show shelf"); //entire shelf
        System.out.println("15. Exit");
        System.out.println();
    }

    /**
     * Completes execution of 3-7
     * @param option
     */
    public void pull(int option){
        Scanner pullOption = new Scanner(System.in);
        switch (option) {
            case 3 -> {
                System.out.println("Enter your author: ");
                String author = pullOption.nextLine();
                pull.pullBooks(authorComp, author);

            }
            case 4 -> {
                System.out.println("Enter your book title: ");
                String title = pullOption.nextLine();
                pull.pullBooks(titleComp, title);

            }
            case 5 -> {
                int priority = -1;
                while (priority > 3 || priority < 1) {
                    System.out.println("Enter your priority level of choice (1-3): ");
                    try {
                        priority = pullOption.nextInt();
                    } catch (InputMismatchException ignored) {
                    }
                }
                pull.pullBooks(priorityComp, priority);

            }
            case 6 -> {
                System.out.println("Enter your series title: ");
                String series = pullOption.nextLine();
                pull.pullBooks(seriesComp, series);

            }
            case 7 -> {
                String[] types = {"fiction", "non-fiction", "comic"};
                System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
                int typeInd = -1;
                while (true) {
                    typeInd = pullOption.nextInt();
                    try {
                        pull.pullShelf(types[typeInd - 1]);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
                    }
                }

            }
        }
    }

    /**
     * Completes execution of options 8-10
     * @param bought executes option 9 if not null
     * @param priority executes option 10 if given number 1-3
     * @param remove executes option 8 if true
     */
    public void upStatusChoice(Boolean bought, Integer priority, boolean remove){
        //get type
        System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");

        String[] types = {"fiction","non-fiction","comic"};
        int typeInd = -1;
        while (true){
            this.scan = new Scanner(System.in);
            try{
                typeInd = this.scan.nextInt();
                if(typeInd > -1 && typeInd < 4) {
                    break;
                }
            }catch (Exception e){
                System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
            }

        }
        String type = types[typeInd-1];

        //get series
        this.scan = new Scanner(System.in);
        System.out.println("If your book(s) are a part of a series enter the series name, if not enter -1");
        String seriesTitle = this.scan.nextLine();
        if(seriesTitle.equals("-1")){
            seriesTitle = null;
        }

        //get vol
        int vol = 0;
        if(seriesTitle != null) {

            System.out.println("If you want to remove all the volumes of a series enter 0." +
                    " If you would like to remove multiple volumes in a series enter -1." +
                    " If you would like to remove one volume of a series, enter the volume number.");
            while(true){
                this.scan = new Scanner(System.in);
                try {
                    vol = this.scan.nextInt();
                    if(vol >= -1){
                        break;
                    }
                    System.out.println("You've entered an invalid number.");
                }catch (InputMismatchException e){
                    System.out.println("Enter a valid number.");
                }
            }
        }

        //get author
        System.out.println("If you know the author enter their name, if not enter -1");
        this.scan = new Scanner(System.in);
        String author = this.scan.nextLine();
        if(author.equals("-1")){
            author = null;
        }

        if(vol == 0){
            System.out.println("Author: "+author+" Type: "+type+" Series: "+seriesTitle);
            update.updateStatus(this.scan,bought,priority,"",author,type,seriesTitle,vol,remove);

        }else {
            //get title
            this.scan = new Scanner(System.in);
            String title = this.scan.next();

            update.updateStatus(this.scan, bought, priority, title, author, type, seriesTitle, vol, remove);
        }

    }

    /**
     * Populates bookshelf
     * @throws Exception
     */
    public void input() throws Exception {
        this.scan = new Scanner(System.in);
        this.input.inputData(this.scan);
    }

    /**
     * Populates bookshelf
     */
    public void csv() {
        this.scan = new Scanner(System.in);
        System.out.println("Enter your filename/path: ");
        String filename = this.scan.next();
        try {
            this.csv.readBook(filename);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Program control where user can execute options given in options() menu
     * @throws Exception
     */
    public void execute() throws Exception {

        Update update = null;
        boolean execute = true;
        boolean shelfFilled = false;
        System.out.println("Welcome to your book shelf.");
        while (execute){
            options();

            System.out.println("Please select an option.");

            int option = -1;

            while (true) {

                try {
                    option = this.scan.nextInt();
                    this.scan = new Scanner(System.in);
                    break;
                } catch (InputMismatchException | IllegalStateException e) {
                    System.out.println("Please enter a number.");
                }
            }


            while (true){


                if(option > 15 || option < 1){
                    System.out.println("Please enter a valid option.");
                }
                else if(option <= 2 || shelfFilled){
                    shelfFilled = true;
                    break;
                }
                System.out.println("Please enter 1 or 2 to populate bookshelf.");
                option = this.scan.nextInt();
                this.scan = new Scanner(System.in);

            }

            switch (option){
                case 1 ->  {
                    input();
                    update = new Update(logger,shelf);

                }

                case 2 -> {
                    csv();
                    update = new Update(logger,shelf);
                    System.out.println("Size: "+update.getPurchased().size());

                }
                case 3 ->{
                    pull(3);
                }

                case 4 -> {
                    pull(4);
                }
                case 5 -> {
                    pull(5);
                }
                case 6 -> {
                    pull(6);
                }
                case 7 -> {
                    pull(7);
                }
                case 8 -> {
                    upStatusChoice(null,null,true);
                }
                case 9 -> {
                    this.scan = new Scanner(System.in);
                    option = -1;

                    while (option > 2 || option < 1) {
                        try {
                            System.out.println("Enter 1 to set books to purchased or 2 to set them to not purchased: ");
                            option = this.scan.nextInt();

                        } catch (InputMismatchException | IllegalStateException e) {
                            System.out.println("Please enter 1 or 2.");
                        }
                    }

                    //if option is 1 bought is true and if option is 2 bought is false
                    upStatusChoice(option == 1, null, false);
                }
                case 10 -> {

                    int priority = -1;
                    do{
                        scan = new Scanner(System.in);
                        System.out.println("Enter priority you want to set book(s) to (1 (high priority), 2 (med priority), or 3 (low priority)): ");
                        priority = scan.nextInt();
                    }while (!(priority > 1 && priority < 4));

                    upStatusChoice(null,priority,false);
                }
                case 11 -> {
                    System.out.println("Shelf size: "+shelf.getBooks().get("fiction").size());
//
                    for (String i : update.getPurchased()) {
                        System.out.println(i);
                    }
                }
                case 12-> {
                    for (String i : update.getTitleNotPurchased()) {
                        System.out.println(i);
                    }
                }
                case 13 -> {
                    this.scan = new Scanner(System.in);
                    System.out.println("What would you like to name your bookshelf file? ");
                    String file = this.scan.next();
                    this.writer.write(file);
                }
                case 14 -> {
                    System.out.println("Fiction books:");
                    shelf.toString("fiction");
                    System.out.println("Non-fiction books:");
                    shelf.toString("non-fiction");
                    System.out.println("Comic books:");
                    shelf.toString("comic");
                }
                case 15 -> {
                    System.out.println("GOODBYE");
                    execute = false;
                }
            }

            this.scan = new Scanner(System.in);
            TimeUnit.SECONDS.sleep(1);

        }
    }


}
