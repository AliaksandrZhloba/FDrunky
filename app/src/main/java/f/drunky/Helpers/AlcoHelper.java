package f.drunky.Helpers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
import f.drunky.mvp.models.UserProfile;

public class AlcoHelper {
    static double K = 0.0056;
    static double Betta60 = 0.11;


    public static State calcState(List<DrunkItem> log, UserProfile userProfile, Date curTime) {
        double r = 0.65;
        double m = 65;

        if (log.size() == 0)
        {
            return State.Sober;
        }
        else
        {
            if (userProfile.isFilled())
            {
                r = userProfile.Gender == GenderHelper.Male.Code ? 0.7 : 0.6;
                m = userProfile.Weight;
            }

            double c = 0;
            Date time = log.get(0).getUseTime();
            for (DrunkItem logItem: log)
            {
                if (logItem.getUseTime().getTime() > curTime.getTime()) {
                    break;
                }

                long ds = TimeUnit.MILLISECONDS.convert(logItem.getUseTime().getTime() - time.getTime(), TimeUnit.MILLISECONDS);
                double c_cur = c - Betta60 * ds / (60d * 60d * 1000d);
                if (c_cur < 0) {
                    c_cur = 0;
                }

                double A = logItem.getAlcohol() * logItem.getVolume() * K;
                double C = A / (m * r);

                c = c_cur + C;
                time = logItem.getUseTime();
            }

            long ds = TimeUnit.MILLISECONDS.convert(curTime.getTime() - time.getTime(), TimeUnit.MILLISECONDS);
            double c_cur = c - Betta60 * ds / (60d * 60d * 1000d);
            if (c_cur < 0) {
                c_cur = 0;
            }

            State state = new State();
            state.Mille = c_cur;
            state.CanDriveInHours = c_cur / Betta60;
            return state;
        }
    }
}
