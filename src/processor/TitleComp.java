package processor;

import util.Book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleComp implements BookComparator{
    @Override
    public <E> boolean eqBook(Book book1, E o) {
        if(book1 == null || o == null){
            return false;
        }

        String title = (String) o;
        String regex = "(?i)" + title;
        Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
        Matcher m = p.matcher(book1.getTitle());
        return m.find();
    }
}
