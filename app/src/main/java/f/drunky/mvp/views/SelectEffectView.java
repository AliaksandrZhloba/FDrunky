package f.drunky.mvp.views;

import com.arellomobile.mvp.MvpView;

import f.drunky.Types.DrinkEffect;

/**
 * Created by AZhloba on 10/9/2017.
 */

public interface SelectEffectView extends MvpView {
    void changeButtonText(int stringId);

    void enableToRelaxOption();
    void enableToHaveAFunOption();
    void enableToDrunkOverOption();
    void enableToContinueAnywayOption();
}
