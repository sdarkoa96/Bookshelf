package ui;

public class Output {

    public Output(){

    }

    public void options(){
        System.out.println("Welcome to your book shelf.");
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
    }
}
