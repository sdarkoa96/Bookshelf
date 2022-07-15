package data;

import util.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CSVReader extends Reader{
    public CSVReader() {
        super();
    }

    @Override
    /**
     * read from a text file that has data to create a book
     */
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
            return;
        }else {
            //TODO: enter code to parse elements o each book by line
            int authorInd = header.indexOf("author");
            int titleInd = header.indexOf("title");
            int typeInd = header.indexOf("type");
            int seriesInd = header.indexOf("series");
            int purchaseInd = header.indexOf("purchased");
            int volInd = header.indexOf("volume");

            String author = null;
            String title = null;
            String type = null;
            String purchase = null;
            String series = null;

            int vol = 0;

            while((firstLine = br.readLine()) != null){
                //not built for titles with commas
                String[] data = firstLine.split(",");
                if(seriesInd != -1){
                    series = data[seriesInd];

                    try{
                        vol = Integer.parseInt(data[volInd]);
                    }catch (NumberFormatException e){
                        vol = 0;
                    }

                }
                author = data[authorInd];
                title = data[titleInd];
                type = data[typeInd];
                if(purchaseInd != -1){
                    purchase = data[purchaseInd];
                }
                newBook(author,title,type,series, vol,purchase);
            }
        }


    }

    @Override
    public void newBook(String author, String title, String type, String seriesTitle, int volume,String purchase){
        Book newBook = new Book(author, title, type, seriesTitle);
        if(purchase != null && purchase.equalsIgnoreCase("true")){
            newBook.setPurchased(true);
            if(volume != 0){
                newBook.setSeriesVol(volume);
            }
        }
        System.out.println("Book added to shelf: "+shelf.addBook(newBook)); //consider using logger for print statement
    }
}
