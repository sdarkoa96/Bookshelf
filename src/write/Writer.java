package write;

import util.Book;
import util.Bookshelf;
import logging.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * creates or updates file representation of Bookshelf
 */

public class Writer {

    Bookshelf shelf;

    public Writer(Bookshelf shelf){
        this.shelf = shelf;
    }

    public void wrtLn(PrintWriter pw, Book i){
        String title = i.getTitle();
        String author = i.getAuthor();
        boolean series = i.getSeries();
        String seriesTitle = null;
        int vol = 0;
        boolean purchased = i.isPurchased();
        String type = i.getType();

        if(series){
            seriesTitle = i.getSeriesTitle();
            vol = i.getSeriesVol();
        }else {
            seriesTitle = "";
        }

        StringBuilder toWrite = new StringBuilder(title+",");
        toWrite.append(author).append(",");
        toWrite.append(seriesTitle).append(",");
        toWrite.append(vol).append(",");
        if(purchased){
            toWrite.append("Y,");
        }else {
            toWrite.append("N,");
        }
        toWrite.append(i.getPriority()).append(",");
        toWrite.append(type);
        pw.write(toWrite.toString());
        pw.write("\n");
    }

    public void write(String filename) throws Exception {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.write("Title,Author,Series,Volume,Purchased,Priority,Type");
        pw.write("\n");

        List<Book> fiction = shelf.getBooks().get("fiction");

        for(Book i: fiction){
            wrtLn(pw,i);
        }
        List<Book> nonFiction = shelf.getBooks().get("non-fiction");
        for(Book i: nonFiction){
            wrtLn(pw,i);
        }
        List<Book> comic = shelf.getBooks().get("comic");
        for(Book i: comic){
            wrtLn(pw,i);
        }

        System.out.println(filename + " was successfully created.");


        fw.close();
        pw.close();

    }
}
