package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.Entity.State;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.TimeHelper;
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
        double minBac = 0.1;
        State state = AlcoHelper.calcState(FDrunkyApplication.INSTANCE.SharedData.DrunkList, FDrunkyApplication.INSTANCE.SharedData.UserProfile, TimeHelper.now());

        if (state.Bac <= AlcoHelper.RelaxBac - minBac) {
            getViewState().enableToRelaxOption();
        }
        else if (state.Bac <= AlcoHelper.FunBac - minBac) {
            getViewState().enableToHaveAFunOption();
        }
        else if (state.Bac <= AlcoHelper.DrunkOverBac - minBac) {
            getViewState().enableToDrunkOverOption();
        }
        else {
            getViewState().enableToContinueAnywayOption();
        }
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

    public void switchToToContinueAnywayEffect() {
        _effect = DrinkEffect.ToContinueAnyway;
        getViewState().changeButtonText(R.string.ToDContinueAnywayButtonText);
    }


    public void SelectEffectClicked() {
        FDrunkyApplication.INSTANCE.SharedData.DrinkEffect = _effect;
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.SELECT_DRINK);
    }
}
