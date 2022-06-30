package processor;

import util.Book;

import java.util.Locale;
import java.util.Objects;

public class SeriesComp implements BookComparator{

    public SeriesComp(){};

    @Override
    public <E> boolean eqBook(Book book1, E series) {
        String seriesStr = (String) series;
        return Objects.equals(book1.getSeriesTitle().toLowerCase(), seriesStr.toLowerCase());
    }


}
