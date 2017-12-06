package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import f.drunky.Entity.Drink;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.presenters.CalcedResultPresenter;

/**
 * Created by AZhloba on 10/15/2017.
 */

public interface CalcedResultView extends MvpView {
    void setMessage(DrinkEffect effect, Drink drink);
}
