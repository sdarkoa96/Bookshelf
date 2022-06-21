import util.Book;

import java.util.*;

class Scratch {
    public void updatePurchaseStatus(Scanner scan, boolean bought, String title, String author, String type, String seriesTitle, int vol){
        List<Book> found = findBook(title,author,type,seriesTitle,vol);
        if(found == null || found.size() == 0){
            System.out.println("Sorry, this book is not in your shelf");
            return;
        }else if(found.size()==1){
            Book selection = found.get(0);
            swapStatus(bought,selection);
            return;
        }

        if (seriesTitle == null && vol == 0){
            //find the single book
            //swapStatus takes care of not entered author

            int number = 1;
            for(Book i: found){
                System.out.println(number+": "+i);
            }

            System.out.println("Enter number to select book or enter -1 to exit: ");
            int index = -1;
            while (true) {
                try {
                    index = scan.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Enter number to select book or enter -1 to exit: ");
                }
            }

            if(index <= 0){
                return;
            }else if(index > found.size()){
                System.out.println("This number is not in the given list");
                return;
            }else {
                Book book = found.get(index-1);
                swapStatus(bought,book);
            }
            return;

        }else if(seriesTitle != null && vol == -1){
            //allow user to pick multiple books
            int number = 1;
            for(Book i: found){
                System.out.println(number+": "+i);
                number++;
            }


            int index = 0;
            while (index != -1) {
                try {
                    System.out.println("Enter number to select book or enter -1 to exit: ");
                    index = scan.nextInt();
                    if(index <= -1){
                        break;
                    }else if(index > found.size()){
                        System.out.println("This number is not in the given list");
                        return;
                    }else {
                        Book book = found.get(index-1);
                        swapStatus(bought,book);
                    }
                }catch (InputMismatchException e){
                    System.out.println("Enter number to select book or enter -1 to exit: ");
                }
            }

            return;

        }else if(seriesTitle != null && vol == 0){
            //change all books in series
            if(author != null){
                for (Book i: found) {
                    swapStatus(bought,i);
                }
            }else{
                //print authors
                ArrayList<String> authorsFound = new ArrayList<>();
                for (Book i: found) {
                    if(!authorsFound.contains(i.getAuthor())){
                        authorsFound.add(i.getAuthor());
                    }
                }

                int number = 1;
                for (String i:authorsFound) { //tree set so order will remain the same
                    System.out.println(number+i);
                    number++;
                }
                //have user choose author
                System.out.println("Enter number to select author: ");
                int index = -1;
                while (true) {
                    try {
                        index = scan.nextInt();
                        if(index > 0 && index <= authorsFound.size()){
                            break;
                        }else{
                            System.out.println("You have not selected a number from the given list. Please select a number: ");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Enter an integer");
                    }
                }

                //select all books of author
                String selection = authorsFound.get(index-1);
                for (Book i: found){
                    if(selection.toLowerCase().equals(i.getAuthor().toLowerCase())){
                        swapStatus(bought,i);
                    }
                }




            }

        }else if(seriesTitle != null && vol > 0){
            //change specific volume status
        }
    }
}