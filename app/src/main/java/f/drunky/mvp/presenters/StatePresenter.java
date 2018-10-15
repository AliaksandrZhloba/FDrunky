package f.drunky.mvp.presenters;

import android.os.CountDownTimer;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import f.drunky.Entity.DrunkItem;
import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.DbReader;
import f.drunky.Helpers.DrinkHelper;
import f.drunky.Navigation.Names.Chains;
import f.drunky.mvp.views.StateView;


@InjectViewState
public class StatePresenter extends MvpPresenter<StateView> {

    private Timer _timer;


    @Override
    protected void onFirstViewAttach() {

        DbReader.loadDrinks();
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(new DrunkItem(Calendar.getInstance().getTime(), DbReader.getDrinks().get(0), 80));
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(new DrunkItem(Calendar.getInstance().getTime(), DbReader.getDrinks().get(0), 90));
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(new DrunkItem(Calendar.getInstance().getTime(), DbReader.getDrinks().get(0), 100));
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(new DrunkItem(Calendar.getInstance().getTime(), DbReader.getDrinks().get(0), 120));

        if (FDrunkyApplication.INSTANCE.SharedData.DrunkList.size() > 0) {
            getViewState().setList(FDrunkyApplication.INSTANCE.SharedData.DrunkList);

            _timer = new Timer("timerName");
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getViewState().refreshList();
                }
            }, 1000, 1000);
        }
        else {
            getViewState().showSober();
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
}
