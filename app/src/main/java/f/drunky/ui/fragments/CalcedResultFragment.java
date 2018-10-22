package f.drunky.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.Entity.Drink;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.presenters.CalcedResultPresenter;
import f.drunky.mvp.views.CalcedResultView;
import f.drunky.ui.dialogs.AskToFillProfileDialog;


public class CalcedResultFragment extends ChainFragment implements CalcedResultView {
    private static final int ASK_TO_FILL_PROFILE = 1;


    @InjectPresenter
    CalcedResultPresenter presenter;


    //private Button _btnWhereToGo;
    private Button _btnGotIt;
    private TextView _txtMessage;
    private ImageView _imgGlass;


    private Drink _drink;


    public CalcedResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calced_result, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        //_btnWhereToGo = getView().findViewById(R.id.btnWhereToGo);
        //_btnWhereToGo.setOnClickListener(l -> presenter.whereToGoClicked());

        _btnGotIt = getView().findViewById(R.id.btnGotIt);
        _btnGotIt.setOnClickListener(l -> presenter.gotItClicked());

        _txtMessage = getView().findViewById(R.id.txtMessage);
        _imgGlass = getView().findViewById(R.id.imgPicture);
    }

    @Override
    public void setMessage(DrinkEffect effect, Drink drink, int volume) {
        _drink = drink;

        _txtMessage.setText(getMessage(effect, drink, volume));
        _imgGlass.setImageBitmap(drink.getImage());
    }

    @Override
    public void showAskToFillProfileDialog() {
        AskToFillProfileDialog askDialog = new AskToFillProfileDialog();
        askDialog.setTargetFragment(this, ASK_TO_FILL_PROFILE);

        askDialog.show(getFragmentManager(), null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ASK_TO_FILL_PROFILE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    presenter.askToFillProfile_OkClicked();
                    break;

                case Activity.RESULT_CANCELED:
                    presenter.askToFillProfile_SkipClicked();
                    break;
            }
        }
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
