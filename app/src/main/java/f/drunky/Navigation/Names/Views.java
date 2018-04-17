package f.drunky.Navigation.Names;

import android.support.v4.app.Fragment;

import java.util.HashMap;

import f.drunky.ui.fragments.AboutFragment;
import f.drunky.ui.fragments.AgreementContentFragment;
import f.drunky.ui.fragments.AgreementFragment;
import f.drunky.ui.fragments.CalcedResultFragment;
import f.drunky.ui.fragments.MapFragment;
import f.drunky.ui.fragments.SelectDrinkFragment;
import f.drunky.ui.fragments.SelectEffectFragment;
import f.drunky.ui.fragments.SettingsFragment;
import f.drunky.ui.fragments.StateFragment;

/**
 * Created by AZhloba on 12/5/2017.
 */

public class Views {
    public static final String SELECT_EFFECT = "SELECT_EFFECT";
    public static final String SELECT_DRINK = "SELECT_DRINK";
    public static final String CALC_RESULT = "CALC_RESULT";
    public static final String STATE = "STATE";
    public static final String MAP = "MAP";
    public static final String AGREEMENT = "AGREEMENT";
    public static final String AGREEMENT_CONTENT = "AGREEMENT_CONTENT";
    public static final String ABOUT = "ABOUT";
    public static final String SETTINGS = "SETTINGS";


    private static HashMap<String, Class<? extends Fragment>> _views = FillViews();

    private static HashMap<String, Class<? extends Fragment>> FillViews() {
        HashMap<String, Class<? extends Fragment>> views = new HashMap<>();
        views.put(SELECT_EFFECT, SelectEffectFragment.class);
        views.put(SELECT_DRINK, SelectDrinkFragment.class);
        views.put(CALC_RESULT, CalcedResultFragment.class);
        views.put(STATE, StateFragment.class);
        views.put(MAP, MapFragment.class);
        views.put(AGREEMENT, AgreementFragment.class);
        views.put(AGREEMENT_CONTENT, AgreementContentFragment.class);
        views.put(ABOUT, AboutFragment.class);
        views.put(SETTINGS, SettingsFragment.class);

        return views;
    }

    public static Class<? extends Fragment> GetFragment(String name) {
        return _views.get(name);
    }
}
