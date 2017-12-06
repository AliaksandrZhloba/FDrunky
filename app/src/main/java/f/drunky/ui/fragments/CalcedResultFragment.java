package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.Entity.Drink;
import f.drunky.Helpers.DrinkEffectHelper;
import f.drunky.Helpers.StatusBarHelper;
import f.drunky.Helpers.Utils;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.presenters.CalcedResultPresenter;
import f.drunky.mvp.views.CalcedResultView;


public class CalcedResultFragment extends ChainFragment implements CalcedResultView {

    @InjectPresenter
    CalcedResultPresenter presenter;


    private Button _btnWhereToGo;
    private Button _btnGotButton;
    private TextView _txtEffect;
    private TextView _txtAction1;
    private TextView _txtAction2;
    private TextView _txtVolume;
    private TextView _txtDrink;
    private TextView _txtSelectedDrink;
    private FrameLayout _cSelectedDrink;
    private ImageView _imgGlass;


    private Drink _drink;


    public CalcedResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        int duration = getContext().getResources().getInteger(R.integer.slide_animation_duration);
        StatusBarHelper.setStatusBarColorWithAnimation(getActivity(), _drink.getAppearance().getStatusBarColor(), duration);
    }

    @Override
    public void onStop() {
        super.onStop();
        int duration = getContext().getResources().getInteger(R.integer.slide_animation_duration);
        StatusBarHelper.setStatusBarColorWithAnimation(getActivity(), Utils.getThemeColor(getView().getContext(), R.attr.colorPrimaryDark), duration);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calced_result, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        _btnWhereToGo = getView().findViewById(R.id.btnWhereToGo);
        _btnGotButton = getView().findViewById(R.id.btnGotIt);

        _txtEffect = getView().findViewById(R.id.txtEffect);
        _txtAction1 = getView().findViewById(R.id.txtAction1);
        _txtAction2 = getView().findViewById(R.id.txtAction2);
        _txtVolume = getView().findViewById(R.id.txtVolume);
        _txtDrink = getView().findViewById(R.id.txtDrink);
        _txtSelectedDrink = getView().findViewById(R.id.txtSelectedDrink);
        _imgGlass = getView().findViewById(R.id.imgGlass);

        _cSelectedDrink = getView().findViewById(R.id.fSelectedDrink);


        ImageButton btnBack = getView().findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> presenter.goBack());

        presenter.init();
    }

    @Override
    public void setMessage(DrinkEffect effect, Drink drink) {
        _drink = drink;

        _btnWhereToGo.setBackgroundColor(drink.getAppearance().getBackgroundColor());
        _btnGotButton.setBackgroundColor(drink.getAppearance().getBackgroundColor());

        _txtEffect.setText(DrinkEffectHelper.toString(effect));
        _txtAction1.setText(DrinkEffectHelper.getAction1(effect));
        _txtAction2.setText(DrinkEffectHelper.getAction2(effect));
        _txtVolume.setText(String.valueOf(DrinkEffectHelper.calcVolume(effect, drink)));
        _txtDrink.setText(drink.getTitle());
        _imgGlass.setImageBitmap(drink.getAppearance().getGlassPicture());

        _txtSelectedDrink.setText(drink.getTitle());
        _txtSelectedDrink.setTextColor(drink.getAppearance().getTextColor());
        _cSelectedDrink.setBackgroundColor(drink.getAppearance().getCaptionBackgroundColor());
    }
}
