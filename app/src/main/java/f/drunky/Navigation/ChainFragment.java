package f.drunky.Navigation;

import com.arellomobile.mvp.MvpAppCompatFragment;

import f.drunky.Navigation.Names.ChainInfo;

/**
 * Created by AZhloba on 12/6/2017.
 */

public class ChainFragment extends MvpAppCompatFragment {
    private ChainInfo _chainInfo;

    public ChainInfo getChainInfo() { return  _chainInfo; }
    public void setChainInfo(ChainInfo chainInfo) { _chainInfo = chainInfo; }
}
