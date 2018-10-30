package f.drunky.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import f.drunky.FDrunkyApplication;
import f.drunky.mvp.models.UserProfile;
import f.drunky.mvp.models.UserSettings;

/**
 * Created by AZhloba on 12/3/2017.
 */

public class SettingsHelper {
    private static final String APP_PREFERENCES = "fdrunkysettings";
    private static final String FIRST_LAUNCH = "first_launch";
    private static final String LANGUAGE = "language";
    private static final String AGE = "age";
    private static final String WEIGHT = "weight";
    private static final String GENDER = "gender";

    private static final int INVALID_AGE_VALUE = -1;
    private static final float INVALID_WEIGHT_VALUE = -1f;

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
        return _preferences.getString(LANGUAGE, LanguageHelper.En.Code);
    }

    public static void setLanguage(String language) {
        _preferences.edit().putString(LANGUAGE, language).commit();
    }


    public static UserSettings loadUserSettings() {
        // TODO: implement cache
        String language = _preferences.getString(LANGUAGE, LanguageHelper.En.Code);

        UserSettings userSettings = new UserSettings();
        userSettings.Language = language;

        return userSettings;
    }

    public static void saveUserSettings(UserSettings userSettings) {
        SharedPreferences.Editor editor = _preferences.edit();

        editor.putString(LANGUAGE, userSettings.Language);

        editor.commit();
    }


    public static UserProfile loadUserProfile() {
        // TODO: implement cache

        int age = _preferences.getInt(AGE, INVALID_AGE_VALUE);
        float weight = _preferences.getFloat(WEIGHT, INVALID_WEIGHT_VALUE);
        String gender = _preferences.getString(GENDER, null);

        UserProfile userProfile = new UserProfile();
        if (age != INVALID_AGE_VALUE) userProfile.Age = age;
        if (weight != INVALID_WEIGHT_VALUE) userProfile.Weight = weight;
        if (gender != null) userProfile.Gender = gender;

        return userProfile;
    }

    public static void saveUserProfile(UserProfile userProfile) {
        SharedPreferences.Editor editor = _preferences.edit();

        if (userProfile.Age != null) {
            editor.putInt(AGE, userProfile.Age);
        }
        else {
            editor.remove(AGE);
        }

        if (userProfile.Weight != null) {
            editor.putFloat(WEIGHT, userProfile.Weight);
        }
        else {
            editor.remove(WEIGHT);
        }

        if (userProfile.Gender != null) {
            editor.putString(GENDER, userProfile.Gender);
        }
        else {
            editor.remove(GENDER);
        }

        editor.commit();
    }
}
