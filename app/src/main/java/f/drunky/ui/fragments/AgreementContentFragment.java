package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;


public class AgreementContentFragment extends ChainFragment {

    public AgreementContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agreement_content, container, false);
    }
}
