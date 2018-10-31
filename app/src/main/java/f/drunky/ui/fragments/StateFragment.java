package f.drunky.ui.fragments;


import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.IBackHandler;
import f.drunky.R;
import f.drunky.mvp.presenters.StatePresenter;
import f.drunky.mvp.views.StateView;
import f.drunky.ui.adapters.DrunkItemsAdapter;
import f.drunky.ui.adapters.SwipeToDeleteCallback;
import f.drunky.ui.views.StateScaleView;


public class StateFragment extends ChainFragment implements StateView, IBackHandler {

    @InjectPresenter
    StatePresenter presenter;

    private RecyclerView _lDrinks;
    private DrunkItemsAdapter _lDrinksAdapted;
    private TextView _txtSober;
    private TextView _txtDriveAvailability;
    private TextView _txtBAC;
    private TextView _txtRelax;
    private TextView _txtFunky;
    private TextView _txtLegless;
    private StateScaleView _stateScaleView;

    private ArrayList<DrunkItem> _drinks;

    private Paint p = new Paint();
    private SwipeToDeleteCallback _swipeToDeleteCallback;


    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        _txtSober = getView().findViewById(R.id.txtSober);
        _txtBAC = getView().findViewById(R.id.txtBAC);
        _txtDriveAvailability = getView().findViewById(R.id.txtDriveAvailability);
        _txtRelax = getView().findViewById(R.id.txtRelax);
        _txtFunky = getView().findViewById(R.id.txtFunky);
        _txtLegless = getView().findViewById(R.id.txtLegless);
        _stateScaleView = getView().findViewById(R.id.StateScaleView);

        _lDrinks = getView().findViewById(R.id.lDrinks);
        _lDrinks.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getView().getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _lDrinks.setLayoutManager(mLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(_lDrinks);
    }


    @Override
    public void setList(ArrayList<DrunkItem> drinks) {
        _drinks = drinks;

        _lDrinksAdapted = new DrunkItemsAdapter(drinks);
        _lDrinks.setAdapter(_lDrinksAdapted);

        _txtSober.setVisibility(View.GONE);
        _lDrinks.setVisibility(View.VISIBLE);
        _lDrinksAdapted.notifyDataSetChanged();

        setUpItemTouchHelper();
    }

    private void setUpItemTouchHelper() {
        _swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final DrunkItem item = _drinks.get(position);

                _lDrinksAdapted.removeItem(position);

                presenter.removeItem(item);

                Snackbar snackbar = Snackbar.make(getView().findViewById(R.id.stateLayout), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", view -> {
                    _lDrinksAdapted.restoreItem(item, position);
                    _lDrinks.scrollToPosition(position);

                    presenter.restoreItem(item);
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(_swipeToDeleteCallback);
        mItemTouchHelper.attachToRecyclerView(_lDrinks);
    }

    @Override
    public void refresh(State state) {
        getActivity().runOnUiThread(() -> {
            if (_lDrinksAdapted.getItemCount() > 0 && !_swipeToDeleteCallback.isSwiping()) {
                _lDrinksAdapted.notifyDataSetChanged();
            }

            updateState(state);
        });
    }

    @Override
    public void updateState(State state) {
        if (_drinks.size() == 0) {
            _lDrinks.setVisibility(View.INVISIBLE);
            _txtSober.setVisibility(View.VISIBLE);
        }
        else {
            _lDrinks.setVisibility(View.VISIBLE);
            _txtSober.setVisibility(View.INVISIBLE);
        }

        if (state.Bac == 0) {
            _txtBAC.setVisibility(View.INVISIBLE);
        }
        else {
            _txtBAC.setText(String.format(getString(R.string.Mille), state.Bac));
            _txtBAC.setVisibility(View.VISIBLE);
        }

        if (state.CanDriveInHours == 0) {
            _txtDriveAvailability.setText(R.string.CanDrive);
        }
        else {
            _txtDriveAvailability.setText(String.format(getString(R.string.CanDriveIn), state.CanDriveInHours));
        }

        _stateScaleView.setState(state.Value);
    }

    @Override
    public void onBackPressed() {
        presenter.backPressed();
    }
}
