package util;

public class Book {

    String author = null;
    String title = null;
    String genre = null;
    String type = null;
    boolean purchased = false;
    boolean series = false;
    String seriesTitle = null;
    int seriesVol = 0;

    public Book(String author, String title, String genre, String type, String seriesTitle) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.seriesTitle = seriesTitle;
    }

    public boolean equals(Book other){

        if(other.getSeries() && this.series){
            if(!(other.getSeriesTitle().equals(this.seriesTitle))){
                return false;
            }
        }
        return (other.getAuthor().equals(this.author) && other.getTitle().equals(this.title) && other.getSeriesVol()==this.getSeriesVol());
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
