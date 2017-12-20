package f.drunky.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DbHelper;
import f.drunky.Helpers.DbReader;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Navigation.Names.ChainInfo;
import f.drunky.Navigation.Names.Chains;
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

        SettingsHelper.init();

        if (SettingsHelper.getIsFirstLaunch()) {
            FDrunkyApplication.INSTANCE.getMenuController().disableMenu();
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.AGREEMENT);
        }
        else {
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
        }
    }


    public void gotoCalculation() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.CALCULATION)) {
            FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
        }
    }

    public void gotoAbout() {
        if (!FDrunkyApplication.INSTANCE.getRouter().isChain(Chains.ABOUT)) {
            FDrunkyApplication.INSTANCE.getRouter().navigateToNewChain(Chains.ABOUT);
       }
    }
}
