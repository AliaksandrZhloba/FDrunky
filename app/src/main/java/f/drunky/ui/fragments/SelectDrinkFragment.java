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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.Helpers.AnimationHelper;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.SelectDrinkPresenter;
import f.drunky.mvp.views.SelectDrinkView;
import f.drunky.ui.adapters.DrinkAutoCompleteAdapter;
import f.drunky.ui.adapters.SelectDrinkAdapter;


public class SelectDrinkFragment extends ChainFragment implements SelectDrinkView {

    @InjectPresenter
    SelectDrinkPresenter presenter;

    private Button _btnCalcHowMuch;
    private TextView _txtNotFound;
    private RecyclerView _lDrinks;
    private AutoCompleteTextView _txtDrink;
    private Unregistrar _unregistrar;
    private TextView _txtQuestion;
    private LinearLayout _lInput;

    private int _questionInitTopMargin;
    private int _questionFinishTopMargin;
    private int _inputInitTopMargin;
    private int _inputFinishTopMargin;
    private int _duration;


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
            HideSoftKeyboard();
            String input = (String) adapterView.getItemAtPosition(position);
            presenter.search(input);
        });
        _txtDrink.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || (keyEvent.getAction() == KeyEvent.KEYCODE_ENTER)) {
                _txtDrink.dismissDropDown();

                String input = textView.getText().toString();
                presenter.search(input);

                HideSoftKeyboard();
                return true;
            }
            return false;
        });

        ImageButton btnSearch = getView().findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            HideSoftKeyboard();
            String input = _txtDrink.getText().toString();
            presenter.search(input);
        });

        _txtQuestion = getView().findViewById(R.id.txtQuestion);
        ViewGroup.MarginLayoutParams  lpQuestion = (ViewGroup.MarginLayoutParams)_txtQuestion.getLayoutParams();
        _questionInitTopMargin = lpQuestion.topMargin;
        _questionFinishTopMargin = 14;

        _lInput = getView().findViewById(R.id.lInput);
        ViewGroup.MarginLayoutParams  lpInput = (ViewGroup.MarginLayoutParams)_lInput.getLayoutParams();
        _inputInitTopMargin = lpInput.topMargin;
        _inputFinishTopMargin = 100;

        _duration = 200;
    }


    private void HideSoftKeyboard() {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(getView().getApplicationWindowToken(), 0);
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
            _btnCalcHowMuch.setBackgroundColor(getActivity().getColor(R.color.mainColor));
            _lDrinks.setVisibility(View.VISIBLE);
            _lDrinks.setAdapter(new SelectDrinkAdapter(drinks));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        _unregistrar.unregister();
    }

    @Override
    public void onResume() {
        super.onResume();

        _unregistrar = KeyboardVisibilityEvent.registerEventListener(
                getActivity(),
                isOpen -> {
                    if (isOpen) {
                        AnimationHelper.animateTopMargin(_txtQuestion, _questionInitTopMargin, _questionFinishTopMargin, _duration);
                        AnimationHelper.animateTopMargin(_lInput, _inputInitTopMargin, _inputFinishTopMargin, _duration);
                    }
                    else {
                        AnimationHelper.animateTopMargin(_txtQuestion, _questionFinishTopMargin, _questionInitTopMargin, _duration);
                        AnimationHelper.animateTopMargin(_lInput, _inputFinishTopMargin, _inputInitTopMargin, _duration);
                    }
                });
    }
}
