package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;
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

        getViewState().setMessage(effect, drink);
    }

    public void goBack() {
        FDrunkyApplication.INSTANCE.getBackController().goBack();
    }
}
