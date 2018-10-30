package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import f.drunky.Entity.Gender;
import f.drunky.Entity.Language;
import f.drunky.mvp.models.UserProfile;
import f.drunky.mvp.models.UserSettings;

/**
 * Created by AZhloba on 1/3/2018.
 */

public interface SettingsView extends MvpView {
    void setLanguages(List<Language> languages);
    void setGenders(List<Gender> genders);
    void setUserProfile(UserProfile userProfile);
    void setUserSettings(UserSettings userSettings);
}
