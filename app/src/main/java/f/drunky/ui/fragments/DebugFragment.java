package f.drunky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.DebugPresenter;
import f.drunky.mvp.views.DebugView;


public class DebugFragment extends ChainFragment implements DebugView {

    @InjectPresenter
    DebugPresenter presenter;


    private TextView _txtEvent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debug, container, false);
    }

    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        _txtEvent = getView().findViewById(R.id.txtEvent);
    }

    public void showDebugInfo() {
        _txtEvent.setText(Integer.toString(FDrunkyApplication.INSTANCE.SharedData.Event));
    }
}
