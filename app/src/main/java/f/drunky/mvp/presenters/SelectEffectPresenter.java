package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.views.SelectEffectView;

/**
 * Created by AZhloba on 10/9/2017.
 */

@InjectViewState
public class SelectEffectPresenter extends MvpPresenter<SelectEffectView> {
    private List<String> _categories;
    private List<Drink> _drinks;
    private DrinkEffect _effect = DrinkEffect.ToRelax;


    @Override
    protected void onFirstViewAttach() {
        loadData();
    }


    public void switchToToRelaxEffect() {
        _effect = DrinkEffect.ToRelax;
        getViewState().changeButtonText(R.string.ToRelaxButtonText);
    }

    public void switchToToHaveAFunEffect() {
        _effect = DrinkEffect.ToHaveAFun;
        getViewState().changeButtonText(R.string.ToHaveAFunButtonText);
    }

    public void switchToToDrunkOverEffect() {
        _effect = DrinkEffect.ToDrunkOver;
        getViewState().changeButtonText(R.string.ToDrunkOverButtonText);
    }


    private void loadData() {
        /*DbHelper dbHelper = new DbHelper(getAttachedViews());
        dbHelper.createDataBases();
        //dbHelper.openDrinksDataBase();

        DbReader reader = new DbReader(dbHelper);
        _categories = reader.LoadCategories();
        _drinks = reader.loadDrinks();

        dbHelper.close();*/
    }

    public void SelectEffectClicked() {
        FDrunkyApplication.INSTANCE.SharedData.DrinkEffect = _effect;
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.SELECT_DRINK);
    }
}
