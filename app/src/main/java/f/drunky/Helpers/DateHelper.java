package f.drunky.Helpers;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateHelper {
    private static DateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

    public static String toString(Date date) {
        return _formatter.format(date);
    }

    public static Date fromString(String str) throws ParseException {
        return _formatter.parse(str);
    }
}
