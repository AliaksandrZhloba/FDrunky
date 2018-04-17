package f.drunky.mvp.presenters;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrinkItem;
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
        if (FDrunkyApplication.INSTANCE.SharedData.DrinkList == null) {
            FDrunkyApplication.INSTANCE.SharedData.DrinkList = new ArrayList<>();
        }

        DrinkItem item = new DrinkItem(Calendar.getInstance().getTime(), FDrunkyApplication.INSTANCE.SharedData.Drink, FDrunkyApplication.INSTANCE.SharedData.Volume);
        FDrunkyApplication.INSTANCE.SharedData.DrinkList.add(item);

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
