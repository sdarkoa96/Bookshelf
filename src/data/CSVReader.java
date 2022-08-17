package data;

import util.Book;
import logging.Logger;
import util.Bookshelf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Reads CSV file to obtain data that will create Book Object
 */
public class CSVReader extends Reader{

    /**
     * logs whether book is added into the shelf
     */
    Logger logger;
    /**
     * holds Book objects in lists representing shelves: fiction, non-fiction, and comic
     */
    Bookshelf shelf;

    public CSVReader(Logger logger, Bookshelf shelf) {
        super();
        this.logger = logger;
        this.shelf = shelf;

    }

    /**
     * Reads file and gets data needed to populate fields of Book object
     * @param filename file name or path entered by user
     * @throws IOException
     * @throws NullPointerException
     * @throws SecurityException
     */
    @Override
    public void readBook(String filename) throws IOException, NullPointerException, SecurityException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String firstLine = br.readLine();
        String[] line = firstLine.split(",");

        List<String> header = new ArrayList<>();
        for(String i: line){
            header.add(i.toLowerCase());
        }

        if(!header.contains("author") && !header.contains("title") && !header.contains("type")){
            System.out.println("Cannot create a book with this file");
            fr.close();
            br.close();

        }else {

            int authorInd = header.indexOf("author");
            int titleInd = header.indexOf("title");
            int typeInd = header.indexOf("type");
            int seriesInd = header.indexOf("series");
            int purchaseInd = header.indexOf("purchased");
            int volInd = header.indexOf("volume");
            int priorityInd = header.indexOf("priority");

            String author = null;
            String title = null;
            String type = null;
            String purchase = null;
            String series = null;
            int priority = 0;
            int vol = 0;

            while((firstLine = br.readLine()) != null){
                //not built for titles with commas
                String[] data = firstLine.split(",");
                if(seriesInd != -1){
                    series = data[seriesInd];
                    if(series.isBlank()){
                        series = null;
                    }

                    try{
                        vol = Integer.parseInt(data[volInd]);
                    }catch (NumberFormatException e){
                        vol = 0;
                    }

                }
                author = data[authorInd];
                title = data[titleInd];
                type = data[typeInd];
                if(!type.equalsIgnoreCase("non-fiction")&&!type.equalsIgnoreCase("fiction")&&!type.equalsIgnoreCase("comic")){
                    continue;
                }
                if(author == null || title == null|| author.isBlank() || title.isBlank()){
                    continue;
                }

                if(purchaseInd != -1){
                    purchase = data[purchaseInd];
                }

                if (priorityInd != -1){
                    try {
                        priority = Integer.parseInt(data[priorityInd]);
                    }catch (Exception ignored){

                    }

                }

                newBook(author,title,type,series, vol,purchase,priority);
            }
        }


    }

    /**
     * Creates Book object using data parsed from readbook()
     * @param author author of book
     * @param title title of book
     * @param type type of book: fiction, non-fiction, or comic
     * @param seriesTitle title of series book belongs to
     * @param volume volume of book in a series
     * @param purchase true if you have purchased book false if you have not
     * @param priority if have not purchased book value is 1(high)-3(low) otherwise defaults to -1
     */
    @Override
    public void newBook(String author, String title, String type, String seriesTitle, int volume,String purchase, int priority){

        Book newBook = new Book(author, title, type, seriesTitle);
        if(volume != 0){
            newBook.setSeriesVol(volume);
        }
        if(purchase == null){
            newBook.setPurchased(false);
        }

        if(purchase != null && (purchase.equalsIgnoreCase("y")||purchase.equalsIgnoreCase("yes")||purchase.equalsIgnoreCase("true"))){
            newBook.setPurchased(true);
            newBook.setPriority(-1);
        }else{
            newBook.setPurchased(false);
            newBook.setPriority(priority);
        }
        logger.logEvent( newBook.getTitle() +" added to shelf: "+shelf.addBook(newBook)); //consider using logger for print statement
    }
}
