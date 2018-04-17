package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.Names.Chains;
import f.drunky.mvp.views.StateView;


@InjectViewState
public class StatePresenter extends MvpPresenter<StateView> {
    @Override
    protected void onFirstViewAttach() {
        getViewState().setList(FDrunkyApplication.INSTANCE.SharedData.DrunkList);
    }

    public void backPressed() {
        FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
    }
}
