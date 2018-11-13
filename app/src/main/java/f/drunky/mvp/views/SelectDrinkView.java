package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import f.drunky.Entity.Drink;

/**
 * Created by AZhloba on 10/15/2017.
 */

public interface SelectDrinkView extends MvpView {
    void setCalcButtonState(boolean isEnabled, int colorId);
    void setupDrinkSearch();
    void showSearchResult(ArrayList<Drink> drinks);
}
