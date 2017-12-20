package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.SelectEffectPresenter;
import f.drunky.mvp.views.SelectEffectView;


public class SelectEffectFragment extends ChainFragment implements SelectEffectView {

    @InjectPresenter
    SelectEffectPresenter presenter;

    private Button _btnSelectEffect;

    public SelectEffectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_effect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        _btnSelectEffect = getView().findViewById(R.id.btnSelectEffect);
        _btnSelectEffect.setOnClickListener(l -> presenter.SelectEffectClicked());

        SetOnCheckedListener(R.id.rbToRelax, () -> { presenter.switchToToRelaxEffect();  });
        SetOnCheckedListener(R.id.rbToHaveAFun, () -> { presenter.switchToToHaveAFunEffect(); });
        SetOnCheckedListener(R.id.rbToDrunkOver, () -> { presenter.switchToToDrunkOverEffect();  });
    }

    private void SetOnCheckedListener(int rButtonId, Runnable run) {
        RadioButton rButton = getView().findViewById(rButtonId);
        rButton.setOnCheckedChangeListener((compoundButton, b) -> { if (rButton.isChecked()) run.run(); });
    }

    @Override
    public void changeButtonText(int stringId) {
        _btnSelectEffect.setText(stringId);
    }
}
