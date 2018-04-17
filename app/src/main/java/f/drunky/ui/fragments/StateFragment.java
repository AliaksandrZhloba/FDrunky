package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import f.drunky.Entity.DrunkItem;
import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.IBackHandler;
import f.drunky.R;
import f.drunky.mvp.presenters.StatePresenter;
import f.drunky.mvp.views.StateView;
import f.drunky.ui.adapters.DrunkItemsAdapter;


public class StateFragment extends ChainFragment implements StateView, IBackHandler {

    @InjectPresenter
    StatePresenter presenter;

    private RecyclerView _lDrinks;


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
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        _lDrinks = getView().findViewById(R.id.lDrinks);
        _lDrinks.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getView().getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _lDrinks.setLayoutManager(mLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(_lDrinks);

        _lDrinks.setAdapter(new DrunkItemsAdapter(FDrunkyApplication.INSTANCE.SharedData.DrunkList));
    }


    @Override
    public void setList(ArrayList<DrunkItem> drinks) {

    }

    @Override
    public void onBackPressed() {
        presenter.backPressed();
    }
}
