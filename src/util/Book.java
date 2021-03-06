package util;

import java.util.Locale;
import java.util.Objects;

/**
 * class representing a Book
 */
public class Book {

    String author = null;
    String title = null;
    String type = null; //fiction,non-fiction,comic

    int priority = -1; //on scale of 1-3 represents how much want to buy this book
    boolean purchased = false;
    boolean series = false;
    String seriesTitle = null;
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

//    @Override
//    public int hashCode() {
//        return Objects.hash(author, title, seriesTitle, seriesVol);
//    }

    @Override
    public boolean equals(Object o){
        Book other = (Book) o;

        if(other.getSeries() && this.series){
            if(!(other.getSeriesTitle().toLowerCase().equals(this.seriesTitle.toLowerCase()))){
                return false;
            }
        }
        return (other.getAuthor().toLowerCase().equals(this.author.toLowerCase()) && other.getTitle().toLowerCase().equals(this.title.toLowerCase())
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book: ");
        sb.append(title);

        if(series){
            sb.append(" Vol: ").append(seriesVol);
        }

        sb.append(" by ").append(author);
        sb.append(" purchased: ").append(purchased);


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

//    @Override
//    public int compareTo(Object o){
//        o = (Book) o;
//        return this.getPriority() - o.getPriority();
//    }
}
