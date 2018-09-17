package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import f.drunky.Entity.DrunkItem;

public interface StateView extends MvpView {
    void setList(ArrayList<DrunkItem> drinks);
    void refreshUseTime();
    void refreshList();

    void notifyItemInserted(int position);
    void notifyItemRemoved(int position);
}
