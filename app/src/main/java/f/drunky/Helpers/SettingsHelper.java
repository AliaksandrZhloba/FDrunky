package f.drunky.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import f.drunky.FDrunkyApplication;

/**
 * Created by AZhloba on 12/3/2017.
 */

public class SettingsHelper {
    private static final String APP_PREFERENCES = "fdrunkysettings";
    private static final String APP_PREFERENCES_FIRST_LAUNCH = "first_launch";

    private static SharedPreferences _preferences;


    public static void init() {
        Context context = FDrunkyApplication.INSTANCE.getBaseContext();
        _preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getIsFirstLaunch() {
        return _preferences.getBoolean(APP_PREFERENCES_FIRST_LAUNCH, true);
    }

    public static void setIsFirstLaunch(boolean value) {
        _preferences.edit().putBoolean(APP_PREFERENCES_FIRST_LAUNCH, value).commit();
    }
}
