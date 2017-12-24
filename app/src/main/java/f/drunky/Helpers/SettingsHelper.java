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
    private static final String FIRST_LAUNCH = "first_launch";
    private static final String LANGUAGE = "language";

    private static SharedPreferences _preferences;


    public static void init() {
        Context context = FDrunkyApplication.INSTANCE.getBaseContext();
        _preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getIsFirstLaunch() {
        return _preferences.getBoolean(FIRST_LAUNCH, true);
    }

    public static void setIsFirstLaunch(boolean value) {
        _preferences.edit().putBoolean(FIRST_LAUNCH, value).commit();
    }


    public static String getLanguage() {
        return _preferences.getString(LANGUAGE, "en");
    }

    public static void setLanguage(String language) {
        _preferences.edit().putString(LANGUAGE, language).commit();
    }
}
