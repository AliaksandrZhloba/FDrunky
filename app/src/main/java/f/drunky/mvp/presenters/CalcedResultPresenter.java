package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;
import java.util.Calendar;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrunkItem;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DrinkEffectHelper;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Navigation.Names.Views;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.models.UserProfileModel;
import f.drunky.mvp.views.CalcedResultView;

/**
 * Created by AZhloba on 10/15/2017.
 */

@InjectViewState
public class CalcedResultPresenter extends MvpPresenter<CalcedResultView> {
    private boolean _askToFillProfile = true;


    public void whereToGoClicked() {
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.MAP);
    }

    public void gotItClicked() {
        Date time = Calendar.getInstance().getTime();
        Drink drink = FDrunkyApplication.INSTANCE.SharedData.Drink;
        DrunkItem item = new DrunkItem(time, drink.getTitle(), drink.getImage(), drink.getAlcohol(), FDrunkyApplication.INSTANCE.SharedData.Volume);
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(0, item);

        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.STATE);
    }

    public void askToFillProfile_OkClicked() {
        _askToFillProfile = false;
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.SETTINGS);
    }

    public void askToFillProfile_SkipClicked() {
        _askToFillProfile = false;
    }

    @Override
    protected void onFirstViewAttach() {
        DrinkEffect effect = FDrunkyApplication.INSTANCE.SharedData.DrinkEffect;
        Drink drink = FDrunkyApplication.INSTANCE.SharedData.Drink;

        UserProfileModel userProfile = SettingsHelper.loadUserProfile();

        int volume = DrinkEffectHelper.calcVolume(effect, drink, userProfile);
        getViewState().setMessage(effect, drink, volume);
        FDrunkyApplication.INSTANCE.SharedData.Volume = volume;

        if (!userProfile.isFilled()) {
            getViewState().showAskToFillProfileDialog();
        }
    }
}
