package processor;

import util.Book;

import java.util.Locale;
import java.util.Objects;

public class SeriesComp implements BookComparator{

    public SeriesComp(){};

    @Override
    public <E> boolean eqBook(Book book1, E series) {
        String seriesStr = null;
        if(series != null){
            seriesStr = (String) series;
            seriesStr = seriesStr.toLowerCase();
        }
        return Objects.equals(book1.getSeriesTitle().toLowerCase(), seriesStr);
    }


}
