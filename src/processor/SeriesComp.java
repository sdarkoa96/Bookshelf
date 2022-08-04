package processor;

import util.Book;

import java.util.Locale;
import java.util.Objects;

public class SeriesComp implements BookComparator{

    public SeriesComp(){};

    @Override
    public <E> boolean eqBook(Book book1, E series) {
        if(book1 == null){
            return false;
        }
        String seriesStr = null;
        if(series != null){
            seriesStr = (String) series;
            seriesStr = seriesStr.toLowerCase();
        }

        if (book1.getSeriesTitle() == null){
            return Objects.equals(book1.getSeriesTitle(), seriesStr);
        }

        return Objects.equals(book1.getSeriesTitle().toLowerCase(), seriesStr);
    }


}
