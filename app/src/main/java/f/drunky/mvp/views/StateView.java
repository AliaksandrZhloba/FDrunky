package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import f.drunky.Entity.DrinkItem;

public interface StateView extends MvpView {
    void setList(List<DrinkItem> drinks);
    void backPressed();
}
