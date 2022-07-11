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
        System.out.println("13. Show shelf"); //entire shelf
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
                    }catch (InputMismatchException ignored){
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

    public void upStatusChoice(Boolean bought, Integer priority, boolean remove){
        //get type
        System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
        Scanner pullOption = new Scanner(System.in);
        String[] types = {"fiction","non-fiction","comic"};
        int typeInd = -1;
        while (true){
            try{
                typeInd = pullOption.nextInt();
                if(typeInd > -1 && typeInd < 4) {
                    break;
                }
            }catch (Exception e){
                System.out.println("Enter 1 for fiction, 2 for non-ficiton, or 3 for comic books: ");
            }
        }
        String type = types[typeInd];

        //get series
        System.out.println("If your book(s) are a part of a series enter the series name, if not enter -1");
        String seriesTitle = pullOption.next();
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
                try {
                    vol = pullOption.nextInt();
                    if(vol >= -1){
                        break;
                    }
                    System.out.println("You've entered an invalid number.");
                }catch (InputMismatchException e){
                    System.out.println("Enter a valid number.");
                }
            }
        }

        //get title
        String title = pullOption.next();

        //get author
        System.out.println("If you know the author enter their name, if not enter -1");
        String author = pullOption.next();
        if(author.equals("-1")){
            author = null;
        }
        update.updateStatus(pullOption,bought,priority,title,author,type,seriesTitle,vol,remove);
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


    public void execute() throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean execute = true;
        while (execute){
            options();
            int option = -1;
            System.out.println("BEGIN OUTPUT");

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
                case 3: pull(3);
                case 4: pull(4);
                case 5: pull(5);
                case 6: pull(6);
                case 7: pull(7);
                case 8: upStatusChoice(null,null,true);
                case 9:
                    option = -1;

                    while (option != 1 || option != 2) {
                        try {
                            System.out.println("Enter 1 to set books to purchased or 2 to set them to not purchased: ");
                            option = scan.nextInt();

                        } catch (InputMismatchException | IllegalStateException e) {
                            System.out.println("Please enter 1 or 2.");
                        }
                    }

                    if(option == 1){
                        upStatusChoice(true,null,false);
                    }else if (option == 2){
                        upStatusChoice(false,null,false);
                    }
                case 10:
                    for(String i: update.getPurchased()){
                        System.out.println(i);
                    }
                case 11:
                    for(String i: update.getTitleNotPurchased()){
                        System.out.println(i);
                    }
                case 12:
                case 13:
                    System.out.println("Fiction books:");
                    shelf.toString("fiction");
                    System.out.println("Non-fiction books:");
                    shelf.toString("non-fiction");
                    System.out.println("Comic books:");
                    shelf.toString("comic");
                case 14:
                    System.out.println("END OUTPUT");
                    execute = false;
            }

        }
        scan.close();
    }


}
