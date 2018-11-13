package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DrinkHelper;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.mvp.views.SelectDrinkView;

/**
 * Created by AZhloba on 10/15/2017.
 */

@InjectViewState
public class SelectDrinkPresenter extends MvpPresenter<SelectDrinkView> {
     private ArrayList<Drink> _shownDrinks;

    public void toCalcedResult(int position) {
        Drink drink = _shownDrinks.get(position);

        FDrunkyApplication.INSTANCE.SharedData.Drink = drink;
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.CALC_RESULT);
    }

    public void search(String input) {
        _shownDrinks = DrinkHelper.findDrinks(FDrunkyApplication.INSTANCE.SharedData.Catalog, input);
        getViewState().showSearchResult(_shownDrinks);
    }

    @Override
    protected void onFirstViewAttach() {
        _shownDrinks = FDrunkyApplication.INSTANCE.SharedData.Drinks;

        getViewState().setCalcButtonState(false, R.color.actionButtonDisabledBackgroundColor);
        getViewState().setupDrinkSearch();
    }

    public ArrayList<String> getSearchDrinkHints(String input) {
        ArrayList<Drink> drinks = DrinkHelper.findDrinks(FDrunkyApplication.INSTANCE.SharedData.Catalog, input);

        ArrayList<String> hints = new ArrayList<>();
        for (Drink drink : drinks) {
            hints.add(drink.getTitle());
        }

        return hints;
    }
}
