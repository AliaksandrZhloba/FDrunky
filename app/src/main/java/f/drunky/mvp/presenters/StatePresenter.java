package f.drunky.mvp.presenters;

import android.os.CountDownTimer;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Timer;
import java.util.TimerTask;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.Names.Chains;
import f.drunky.mvp.views.StateView;


@InjectViewState
public class StatePresenter extends MvpPresenter<StateView> {

    private Timer _timer;


    @Override
    protected void onFirstViewAttach() {
        getViewState().setList(FDrunkyApplication.INSTANCE.SharedData.DrunkList);

        _timer = new Timer("timerName");
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getViewState().refreshList();
            }
        }, 1000, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _timer.cancel();
    }

    public void backPressed() {
        FDrunkyApplication.INSTANCE.getRouter().startNewChain(Chains.CALCULATION);
    }
}
