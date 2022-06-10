package data;

import util.Bookshelf;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Reader {

    String filename;

    Bookshelf shelf = Bookshelf.getShelf();

    public Reader(String filename){
        this.filename = filename;
    }

    public abstract void readBook() throws IOException;
    public abstract void newBook(String author, String title, String type, String seriesTitle);

}
