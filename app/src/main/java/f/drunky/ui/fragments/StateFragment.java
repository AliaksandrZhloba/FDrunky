package f.drunky.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import f.drunky.Entity.DrinkItem;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.IBackHandler;
import f.drunky.R;
import f.drunky.mvp.presenters.SettingsPresenter;
import f.drunky.mvp.presenters.StatePresenter;
import f.drunky.mvp.views.StateView;


public class StateFragment extends ChainFragment implements StateView, IBackHandler {

    @InjectPresenter
    StatePresenter presenter;

    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state, container, false);
    }


    @Override
    public void setList(List<DrinkItem> drinks) {

    }

    @Override
    public void backPressed() {

    }

    @Override
    public void onBackPressed() {
        presenter.backPressed();
    }
}
