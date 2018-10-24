package f.drunky.Helpers;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateHelper {
    private static DateFormat _formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");

    public static String ToString(Date date) {
        return _formatter.format(date);
    }

    public static Date FromString(String str) throws ParseException {
        return _formatter.parse(str);
    }
}
