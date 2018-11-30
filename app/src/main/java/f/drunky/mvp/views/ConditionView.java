package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;

public interface ConditionView extends MvpView {
    void setList(ArrayList<DrunkItem> drinks);
    void refresh(State state);
    void updateState(State state);
    void animateFabAddDrink();
}
