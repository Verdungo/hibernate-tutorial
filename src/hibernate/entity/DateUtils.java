package hibernate.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public static Date parseDate(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }

    public static String formatDate(Date date) {
        return formatter.format(date);
    }
}
