package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Navigation.Names.Chains;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.mvp.views.AgreementView;

/**
 * Created by AZhloba on 12/3/2017.
 */

@InjectViewState
public class AgreementPresenter extends MvpPresenter<AgreementView> {
    public void setUserAgree(boolean agree) {
        if (agree) {
            getViewState().setContinueButtonState(true, R.color.mainColor);
        }
        else {
            getViewState().setContinueButtonState(false, R.color.actionButtonDisabledBackgroundColor);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        setUserAgree(false);
    }

    public void continueClicked() {
        SettingsHelper.setIsFirstLaunch(false);
        FDrunkyApplication.INSTANCE.getMenuController().enableMenu();

        FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
    }

    public void showAgreement() {
        FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.AGREEMENT_CONTENT);
    }
}
