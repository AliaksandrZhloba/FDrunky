package f.drunky.ui.fragments;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;


public class AboutFragment extends ChainFragment {

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        TextView txtAgreementLink = getView().findViewById(R.id.txtAgreementLink);
        txtAgreementLink.setPaintFlags(txtAgreementLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtAgreementLink.setOnClickListener(l -> FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.AGREEMENT_CONTENT));

        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            String version = pInfo.versionName;

            TextView txtVersion = getView().findViewById(R.id.txtVersion);
            txtVersion.setText(version);

        } catch (PackageManager.NameNotFoundException e) {

        }
    }
}
