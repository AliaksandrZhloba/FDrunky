package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrunkItem;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.DbReader;
import f.drunky.Helpers.TimeHelper;
import f.drunky.Navigation.Names.Chains;
import f.drunky.Navigation.Names.Views;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.models.UserProfile;
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
        DrunkItem item = new DrunkItem(FDrunkyApplication.INSTANCE.SharedData.Event, time, drink.getTitle(), drink.getImage(), drink.getAlcohol(), FDrunkyApplication.INSTANCE.SharedData.Volume);
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(0, item);
        DbReader.saveLogItem(item);

        FDrunkyApplication.INSTANCE.getRouter().navigateToNewChain(Chains.CONDITION);
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

        UserProfile userProfile = FDrunkyApplication.INSTANCE.SharedData.UserProfile;
        ArrayList<DrunkItem> drunkList = FDrunkyApplication.INSTANCE.SharedData.DrunkList;

        int volume = AlcoHelper.calcRequiredVolume(drunkList, userProfile, TimeHelper.now(), effect, drink);
        getViewState().setMessage(effect, drink, volume);
        FDrunkyApplication.INSTANCE.SharedData.Volume = volume;

        if (!userProfile.isFilled()) {
            getViewState().showAskToFillProfileDialog();
        }
    }
}
