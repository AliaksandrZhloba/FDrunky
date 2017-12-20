package f.drunky.mvp.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DrinkEffectHelper;
import f.drunky.Navigation.Names.Views;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.views.CalcedResultView;

/**
 * Created by AZhloba on 10/15/2017.
 */

@InjectViewState
public class CalcedResultPresenter extends MvpPresenter<CalcedResultView> {
    public void init() {
        DrinkEffect effect = FDrunkyApplication.INSTANCE.SharedData.DrinkEffect;
        Drink drink = FDrunkyApplication.INSTANCE.SharedData.Drink;

        int volume = DrinkEffectHelper.calcVolume(effect, drink);
        getViewState().setMessage(effect, drink, volume);
    }

    public void goBack() {
        FDrunkyApplication.INSTANCE.getBackController().goBack();
    }

    public void whereToGoClicked() {
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.MAP);
    }

    public void gotItClicked() {
        FDrunkyApplication.INSTANCE.getRouter().showSystemMessage("Tra");
    }
}
