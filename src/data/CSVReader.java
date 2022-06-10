package data;

import util.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader extends Reader{
    public CSVReader(String filename) {
        super(filename);
    }

    @Override
    /**
     * read from a text file that has data to create a book
     */
    public void readBook() throws IOException {
        FileReader fr = new FileReader(this.filename);
        BufferedReader br = new BufferedReader(fr);

        String firstLine = br.readLine();
        String[] line = firstLine.split(",");

        List<String> header = new ArrayList<>(Arrays.asList(line));
        //TODO: go through header  and make everything lower case

        if(!header.contains("author") && !header.contains("title") && !header.contains("type")){
            System.out.println("Cannot create a book with this file");
            fr.close();
            br.close();
            return;
        }else {
            //TODO: enter code to parse elements o each book by line
        }


    }

    @Override
    public void newBook(String author, String title, String type, String seriesTitle){
        Book newBook = new Book(author, title, type, seriesTitle);
        System.out.println("Book added to shelf: "+shelf.addBook(newBook)); //consider using logger for print statement
    }
}
