package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import f.drunky.Entity.Gender;
import f.drunky.Entity.Language;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.GenderHelper;
import f.drunky.Helpers.LanguageHelper;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.mvp.models.UserProfile;
import f.drunky.mvp.models.UserSettings;
import f.drunky.mvp.views.SettingsView;

/**
 * Created by AZhloba on 1/3/2018.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    private UserProfile _userProfile;


    @Override
    protected void onFirstViewAttach() {
        List<Language> languages = LanguageHelper.getLanguages();
        getViewState().setLanguages(languages);

        List<Gender> genders = GenderHelper.getGenders();
        getViewState().setGenders(genders);

        UserSettings userSettings = SettingsHelper.loadUserSettings();
        getViewState().setUserSettings(userSettings);

        _userProfile = SettingsHelper.loadUserProfile();
        getViewState().setUserProfile(_userProfile);
    }

    public void languageChanged(Language language) {
        SettingsHelper.setLanguage(language.Code);
        FDrunkyApplication.INSTANCE.LanguageController.setLanguage(language.Code);
    }

    public void genderChanged(Gender gender) {
        _userProfile.Gender = gender.Code;

        // TODO: save UserProfile once (on leave SettingsFragment)
        SettingsHelper.saveUserProfile(_userProfile);
    }

    public void ageChanged(Integer age) {
        _userProfile.Age = age;

        // TODO: save UserProfile once (on leave SettingsFragment)
        SettingsHelper.saveUserProfile(_userProfile);
    }

    public void weightChanged(Float weight) {
        _userProfile.Weight = weight;

        // TODO: save UserProfile once (on leave SettingsFragment)
        SettingsHelper.saveUserProfile(_userProfile);
    }
}
