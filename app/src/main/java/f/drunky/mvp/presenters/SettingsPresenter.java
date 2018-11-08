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

   @Override
    protected void onFirstViewAttach() {
        List<Language> languages = LanguageHelper.getLanguages();
        getViewState().setLanguages(languages);

        List<Gender> genders = GenderHelper.getGenders();
        getViewState().setGenders(genders);

        getViewState().setUserProfile(FDrunkyApplication.INSTANCE.SharedData.UserProfile);

        getViewState().setUserSettings(FDrunkyApplication.INSTANCE.SharedData.UserSettings);
   }

    @Override
    public void detachView(SettingsView view) {
        super.detachView(view);

        SettingsHelper.saveUserProfile(FDrunkyApplication.INSTANCE.SharedData.UserProfile);
        SettingsHelper.saveUserSettings(FDrunkyApplication.INSTANCE.SharedData.UserSettings);
    }

    public void languageChanged(Language language) {
        SettingsHelper.setLanguage(language.Code);
        FDrunkyApplication.INSTANCE.LanguageController.setLanguage(language.Code);
    }

    public void genderChanged(Gender gender) {
        FDrunkyApplication.INSTANCE.SharedData.UserProfile.Gender = gender.Code;
    }

    public void ageChanged(Integer age) {
        FDrunkyApplication.INSTANCE.SharedData.UserProfile.Age = age;
    }

    public void weightChanged(Float weight) {
        FDrunkyApplication.INSTANCE.SharedData.UserProfile.Weight = weight;
    }
}
