package f.drunky.Helpers;

import java.util.Date;
import java.util.Calendar;


public class TimeHelper {
    public static Date now() {
        return Calendar.getInstance().getTime();
    }
}
