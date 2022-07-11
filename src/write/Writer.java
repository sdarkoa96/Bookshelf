package write;

import util.Book;
import util.Bookshelf;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * creates or updates file representation of Bookshelf
 */

public class Writer {

    Bookshelf shelf = Bookshelf.getShelf();

    public Writer(){

    }

    public void write(String filename, Scanner scan) throws IOException {
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);

        pw.write("Title, Author, Series, Volume, Purchased, Priority, Type");

        List<Book> fiction = shelf.getBooks().get("fiction");

        for(Book i: fiction){
            String title = i.getTitle();
            String author = i.getAuthor();
            boolean series = i.getSeries();
            String seriesTitle = null;
            int vol = 0;
            boolean purchased = i.isPurchased();
            String type = i.getType();

            if(series){
                seriesTitle = i.getSeriesTitle();
            }else {
                seriesTitle = "";
            }

            pw.write(title,author);
        }
        List<Book> nonFiction = shelf.getBooks().get("non-fiction");
        List<Book> comic = shelf.getBooks().get("comic");




    }
}
