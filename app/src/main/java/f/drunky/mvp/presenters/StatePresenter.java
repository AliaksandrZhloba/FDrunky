package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Timer;
import java.util.TimerTask;

import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.DbReader;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Helpers.TimeHelper;
import f.drunky.Navigation.Names.Chains;
import f.drunky.mvp.views.StateView;


@InjectViewState
public class StatePresenter extends MvpPresenter<StateView> {

    private Timer _timer;


    @Override
    protected void onFirstViewAttach() {
        recalcState();

        getViewState().setList(FDrunkyApplication.INSTANCE.SharedData.DrunkList);
        getViewState().updateState(FDrunkyApplication.INSTANCE.SharedData.State);

        _timer = new Timer("updateStateTimer");
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                recalcState();
                getViewState().refresh(FDrunkyApplication.INSTANCE.SharedData.State);
            }
        }, 1000, 1000);
    }

    private void recalcState() {
        FDrunkyApplication.INSTANCE.SharedData.State = AlcoHelper.calcState(FDrunkyApplication.INSTANCE.SharedData.DrunkList, SettingsHelper.loadUserProfile(), TimeHelper.now());
        if (FDrunkyApplication.INSTANCE.SharedData.State.Bac == 0) {
            FDrunkyApplication.INSTANCE.SharedData.Event = DbReader.getLastEvent() + 1;
            FDrunkyApplication.INSTANCE.SharedData.DrunkList.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (_timer != null)
            _timer.cancel();
    }

    public void backPressed() {
        FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
    }

    public void removeItem(DrunkItem item) {
        DbReader.deleteLogItem(item);
        recalcState();
        getViewState().updateState(FDrunkyApplication.INSTANCE.SharedData.State);
    }

    public void restoreItem(DrunkItem item) {
        DbReader.saveLogItem(item);
        recalcState();
        getViewState().updateState(FDrunkyApplication.INSTANCE.SharedData.State);
    }
}
