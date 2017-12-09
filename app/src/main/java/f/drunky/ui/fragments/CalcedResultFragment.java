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
import f.drunky.Helpers.DrinkHelper;
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
    private TextView _txtMessage;
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
        _btnWhereToGo.setOnClickListener(l -> presenter.whereToGoClicked());

        _btnGotButton = getView().findViewById(R.id.btnGotIt);
        _btnGotButton.setOnClickListener(l -> presenter.gotItClicked());

        _txtMessage = getView().findViewById(R.id.txtMessage);
        _txtSelectedDrink = getView().findViewById(R.id.txtSelectedDrink);
        _imgGlass = getView().findViewById(R.id.imgGlass);

        _cSelectedDrink = getView().findViewById(R.id.fSelectedDrink);

        ImageButton btnBack = getView().findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> presenter.goBack());

        Button btnGotIt = getView().findViewById(R.id.btnGotIt);

        presenter.init();
    }

    @Override
    public void setMessage(DrinkEffect effect, Drink drink, int volume) {
        _drink = drink;

        _btnWhereToGo.setBackgroundColor(drink.getAppearance().getBackgroundColor());
        _btnGotButton.setBackgroundColor(drink.getAppearance().getBackgroundColor());

        _txtMessage.setText(getMessage(effect, drink, volume));
        _imgGlass.setImageBitmap(drink.getAppearance().getGlassPicture());

        _txtSelectedDrink.setText(drink.getTitle());
        _txtSelectedDrink.setTextColor(drink.getAppearance().getTextColor());
        _cSelectedDrink.setBackgroundColor(drink.getAppearance().getCaptionBackgroundColor());
    }

    private String getMessage(DrinkEffect effect, Drink drink, int volume) {
        int messageId = 0;
        switch (effect){
            case ToRelax:
                messageId = R.string.ToRelaxMessage;
                break;

            case ToHaveAFun:
                messageId = R.string.ToHaveAFunMessage;
                break;

            case ToDrunkOver:
                messageId = R.string.ToDrunkOverMessage;
                break;
        }

        return String.format(getString(messageId), volume, drink.getTitle());
    }
}
