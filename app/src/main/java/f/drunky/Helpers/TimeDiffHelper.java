package f.drunky.Helpers;

import java.util.Calendar;
import java.util.Date;

import f.drunky.FDrunkyApplication;
import f.drunky.R;

public class TimeDiffHelper {
    public static String getPassedTime(Date from) {
        Date now = Calendar.getInstance().getTime();
        long diff = now.getTime() - from.getTime();

        if (diff < 60 * 1000)
            return "";

        long diffHours = diff / (60 * 60 * 1000);
        long diffMinutes = diff / (60 * 1000) - diffHours * 60;

        if (diffHours > 0) {
            String hmFormat = FDrunkyApplication.INSTANCE.getResources().getString(R.string.DiffHoursMinutes);
            return String.format(hmFormat, diffHours, diffMinutes);
        }
        else {
            String mFormat = FDrunkyApplication.INSTANCE.getResources().getString(R.string.DiffMinutes);
            return String.format(mFormat, diffMinutes);
        }
    }
}
