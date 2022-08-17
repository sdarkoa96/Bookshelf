package util;

import java.util.Locale;
import java.util.Objects;

/**
 * Represents a Book
 */
public class Book {

    /**
     * book author
     */
    String author = null;
    /**
     * book title
     */
    String title = null;
    /**
     * fiction,non-fiction,comic
     */
    String type = null;

    /**
     * on scale of 1-3 represents how much user wants to buy this book; remains -1 if book has already been purchased
     */
    int priority = -1;

    /**
     * status indicating whether book has been purchased yes: true no: false
     */
    boolean purchased = false;
    /**
     * indicates whether book is part of a series
     */
    boolean series = false;

    /**
     * title of series book belongs to
     */
    String seriesTitle = null;

    /**
     * volume of book in the series; remains 0 if book doesn't belong in a series
     */
    int seriesVol = 0;

    public Book(String author, String title, String type, String seriesTitle) {
        this.author = author;
        this.title = title;
        this.type = type;
        this.seriesTitle = seriesTitle;
        if(seriesTitle != null){
            this.series = true;
        }
    }

    /**
     * determines if 2 Book objects are equal
     * @param o
     * @return true if books are the same
     */
    @Override
    public boolean equals(Object o){
        Book other = (Book) o;

        if(other.getSeries() && this.series){
            if(!(other.getSeriesTitle().equalsIgnoreCase(this.seriesTitle))){
                return false;
            }
        }
        return (other.getAuthor().equalsIgnoreCase(this.author) && other.getTitle().equalsIgnoreCase(this.title)
                && other.getSeriesVol()==this.getSeriesVol());
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesVol(int vol){
        this.seriesVol = vol;
    }

    public int getSeriesVol(){
        return seriesVol;
    }

    public boolean getSeries(){
        return series;
    }

    public void setPurchased(boolean purchased){
        this.purchased = purchased;
    }

    public boolean isPurchased(){
        return purchased;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Creates string representing a Book object
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book: ");
        sb.append(this.title);

        if(this.series){
            sb.append(" Vol: ").append(this.seriesVol);
        }

        sb.append(" by ").append(this.author);
        sb.append(" purchased: ").append(this.purchased);


        return sb.toString();
    }

    /**
     *
     * @param book
     * @return int determining sorting order (lower numbers at the beginning of list)
     */
    public int compareTo(Book book) {
        return this.getPriority() - book.getPriority();
    }


}
