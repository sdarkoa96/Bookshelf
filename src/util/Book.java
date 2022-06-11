package util;

import java.util.Locale;
import java.util.Objects;

public class Book {

    String author = null;
    String title = null;
    String type = null;

    int priority = -1;
    boolean purchased = false;
    boolean series = false;
    String seriesTitle = null;
    int seriesVol = 0;

    public Book(String author, String title, String type, String seriesTitle) {
        this.author = author;
        this.title = title;
        this.type = type;
//        this.priority = priority;
        this.seriesTitle = seriesTitle;
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

//    @Override
    public int compareTo(Book o){
        return this.getPriority() - o.getPriority();
    }
}
