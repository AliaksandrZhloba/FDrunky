package f.drunky.Navigation.Names;

import f.drunky.R;

/**
 * Created by AZhloba on 12/6/2017.
 */

public class Chains {
    public static final ChainInfo CONDITION = ChainInfo.create("CONDITION", R.id.nav_state, R.string.condition_chain_title, Views.STATE);
    public static final ChainInfo CALCULATION = ChainInfo.create("CALCULATION", R.id.nav_calculation, R.string.calculation_chain_title, Views.SELECT_EFFECT);
    public static final ChainInfo AGREEMENT = ChainInfo.create("AGREEMENT", -1, R.string.agreement_chain_title, Views.AGREEMENT);
    public static final ChainInfo ABOUT = ChainInfo.create("ABOUT", R.id.nav_about, R.string.about_chain_title, Views.ABOUT);
    public static final ChainInfo SETTINGS = ChainInfo.create("SETTINGS", R.id.nav_settings, R.string.settings_chain_title, Views.SETTINGS);

    public static final ChainInfo DEBUG = ChainInfo.create("DEBUG", R.id.nav_debug, R.string.debug_chain_title, Views.DEBUG);
}
