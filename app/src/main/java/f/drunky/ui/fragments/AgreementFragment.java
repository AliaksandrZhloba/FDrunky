package f.drunky.ui.fragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.AgreementPresenter;
import f.drunky.mvp.views.AgreementView;


public class AgreementFragment extends ChainFragment implements AgreementView {

    @InjectPresenter
    AgreementPresenter presenter;


    private Button _btnContinue;


    public AgreementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agreement, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        CheckBox cbAgree = getView().findViewById(R.id.cbAgree);
        cbAgree.setOnClickListener(view -> presenter.setUserAgree(cbAgree.isChecked()));

        TextView txtAgreementLink = getView().findViewById(R.id.txtAgreementLink);
        txtAgreementLink.setPaintFlags(txtAgreementLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtAgreementLink.setOnClickListener(l -> presenter.showAgreement());

        _btnContinue = getView().findViewById(R.id.btnContinue);
        _btnContinue.setOnClickListener(view -> presenter.continueClicked());
    }

    @Override
    public void setContinueButtonState(boolean isEnabled, int colorId) {
        _btnContinue.setEnabled(isEnabled);
        _btnContinue.setBackgroundColor(getActivity().getColor(colorId));
    }
}
