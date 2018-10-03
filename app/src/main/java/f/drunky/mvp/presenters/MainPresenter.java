package f.drunky.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DbHelper;
import f.drunky.Helpers.DbReader;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Navigation.Names.Chains;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.mvp.views.MainView;

/**
 * Created by AZhloba on 10/30/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        Context context = FDrunkyApplication.INSTANCE.getBaseContext();

        DbHelper dbHelper = new DbHelper(context);
        dbHelper.createDataBase();
        DbReader.init(dbHelper);

        //String language = SettingsHelper.getLanguage();
        //FDrunkyApplication.INSTANCE.LanguageController.setLanguage(language);

        if (SettingsHelper.getIsFirstLaunch()) {
            FDrunkyApplication.INSTANCE.getMenuController().disableMenu();
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.AGREEMENT);
        }
        else {
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
        }
    }


    public void navigate(int itemId) {
        switch (itemId) {
            case R.id.nav_state:
                gotoState();
                break;

            case R.id.nav_calculation:
                gotoCalculation();
                break;

            case R.id.nav_settings:
                gotoSettings();
                break;

            case R.id.nav_about:
                gotoAbout();
                break;

            // debug
            case R.id.nav_result:
                FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.CALC_RESULT);
                break;

            case R.id.nav_map:
                FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.MAP);
                break;
        }
    }


    private void gotoState() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.STATE)) {
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.STATE);
        }
    }

    private void gotoCalculation() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.CALCULATION)) {
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
        }
    }

    private void gotoSettings() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.SETTINGS)) {
            FDrunkyApplication.INSTANCE.getRouter().navigateToNewChain(Chains.SETTINGS);
        }
    }

    private void gotoAbout() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.ABOUT)) {
            FDrunkyApplication.INSTANCE.getRouter().navigateToNewChain(Chains.ABOUT);
        }
    }
}
