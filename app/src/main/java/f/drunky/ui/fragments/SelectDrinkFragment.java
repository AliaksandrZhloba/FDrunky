package f.drunky.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.SelectDrinkPresenter;
import f.drunky.mvp.views.SelectDrinkView;
import f.drunky.ui.adapters.DrinkAutoCompleteAdapter;
import f.drunky.ui.adapters.DrinksAdapter;


public class SelectDrinkFragment extends ChainFragment implements SelectDrinkView {

    @InjectPresenter
    SelectDrinkPresenter presenter;

    private Button _btnCalcHowMuch;
    private TextView _txtNotFound;
    private RecyclerView _lDrinks;
    private AutoCompleteTextView _txtDrink;


    public SelectDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_drink, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        _btnCalcHowMuch = getView().findViewById(R.id.btnHowMuch);
        _btnCalcHowMuch.setOnClickListener(view -> {
            int position = getSelectedDrinkIndex();
            presenter.toCalcedResult(position);
        });

        _txtNotFound = getView().findViewById(R.id.txtNotFound);

        _lDrinks = getView().findViewById(R.id.lDrinks);
        _lDrinks.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getView().getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _lDrinks.setLayoutManager(mLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(_lDrinks);

        _txtDrink = getView().findViewById(R.id.txtDrink);
        _txtDrink.setThreshold(1);
        _txtDrink.setOnItemClickListener((adapterView, view, position, l) -> {
            HideSoftKeyboard(view);
            String input = (String) adapterView.getItemAtPosition(position);
            presenter.search(input);
        });
        _txtDrink.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || (keyEvent.getAction() == KeyEvent.KEYCODE_ENTER)) {
                _txtDrink.dismissDropDown();

                String input = textView.getText().toString();
                presenter.search(input);

                HideSoftKeyboard(textView);
                return true;
            }
            return false;
        });

        ImageButton btnSearch = getView().findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            HideSoftKeyboard(view);
            String input = _txtDrink.getText().toString();
            presenter.search(input);
        });

        ImageButton btnBack = getView().findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> presenter.goBack());
    }


    private void HideSoftKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    private int getSelectedDrinkIndex() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager)_lDrinks.getLayoutManager());
        int drinkItemPosition = layoutManager.findFirstVisibleItemPosition();

        return drinkItemPosition;
    }


    @Override
    public void setCalcButtonState(boolean isEnabled, int colorId) {
        _btnCalcHowMuch.setEnabled(isEnabled);
        _btnCalcHowMuch.setBackgroundColor(getActivity().getColor(colorId));
    }

    @Override
    public void setupDrinkSearch(List<String> categories, List<Drink> drinks) {
        _txtDrink.setAdapter(new DrinkAutoCompleteAdapter(getActivity(), categories, drinks));
    }

    @Override
    public void showSearchResult(List<Drink> drinks) {
        if (drinks.size() == 0) {
            _lDrinks.setVisibility(View.GONE);
            _txtNotFound.setVisibility(View.VISIBLE);
            _btnCalcHowMuch.setEnabled(false);
            _btnCalcHowMuch.setBackgroundColor(getActivity().getColor(R.color.actionButtonDisabledBackgroundColor));
        }
        else {
            _txtNotFound.setVisibility(View.GONE);
            _btnCalcHowMuch.setEnabled(true);
            _btnCalcHowMuch.setBackgroundColor(getActivity().getColor(R.color.actionButtonBackgroundColor));
            _lDrinks.setVisibility(View.VISIBLE);
            _lDrinks.setAdapter(new DrinksAdapter(drinks));
        }
    }
}
