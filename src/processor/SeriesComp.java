package processor;

import util.Book;

import java.util.Locale;
import java.util.Objects;

public class SeriesComp implements BookComparator{

    public SeriesComp(){};

    @Override
    public boolean eqBook(Book book1, Book book2) {
        return Objects.equals(book1.getSeriesTitle().toLowerCase(),book2.getSeriesTitle().toLowerCase());
    }
}
