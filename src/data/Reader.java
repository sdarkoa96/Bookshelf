package data;

import util.Bookshelf;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Reader {



    Bookshelf shelf = Bookshelf.getShelf();

    public Reader(){

    }

    public abstract void readBook(String filename) throws IOException;
    public abstract void newBook(String author, String title, String type, String seriesTitle, int volume, String purchase);

}
