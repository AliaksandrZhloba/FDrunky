package f.drunky.Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import f.drunky.Entity.Language;
import f.drunky.FDrunkyApplication;
import f.drunky.mvp.models.UserProfileModel;
import f.drunky.mvp.models.UserSettingsModel;

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


    public static UserSettingsModel loadUserSettings() {
        // TODO: implement cache
        String language = _preferences.getString(LANGUAGE, LanguageHelper.En.Code);

        UserSettingsModel userSettings = new UserSettingsModel();
        userSettings.Language = language;

        return userSettings;
    }

    public static void saveUserSettings(UserSettingsModel userSettings) {
        SharedPreferences.Editor editor = _preferences.edit();

        editor.putString(LANGUAGE, userSettings.Language);

        editor.commit();
    }


    public static UserProfileModel loadUserProfile() {
        // TODO: implement cache

        int age = _preferences.getInt(AGE, INVALID_AGE_VALUE);
        float weight = _preferences.getFloat(WEIGHT, INVALID_WEIGHT_VALUE);
        String gender = _preferences.getString(GENDER, null);

        UserProfileModel userProfile = new UserProfileModel();
        if (age != INVALID_AGE_VALUE) userProfile.Age = age;
        if (weight != INVALID_WEIGHT_VALUE) userProfile.Weight = weight;
        if (gender != null) userProfile.Gender = gender;

        return userProfile;
    }

    public static void saveUserProfile(UserProfileModel userProfile) {
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
