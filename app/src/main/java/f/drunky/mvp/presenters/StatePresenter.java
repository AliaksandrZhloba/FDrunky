package f.drunky.mvp.presenters;

import com.arellomobile.mvp.MvpPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.Names.Chains;
import f.drunky.mvp.views.StateView;


public class StatePresenter extends MvpPresenter<StateView> {
    @Override
    protected void onFirstViewAttach() {
    }

    public void backPressed() {
        FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
    }
}
