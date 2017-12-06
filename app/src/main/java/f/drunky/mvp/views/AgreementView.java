package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by AZhloba on 12/3/2017.
 */

public interface AgreementView extends MvpView {
    void setContinueButtonState(boolean isEnabled, int colorId);
}
