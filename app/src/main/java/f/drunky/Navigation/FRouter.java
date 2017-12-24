package f.drunky.Navigation;

import f.drunky.Navigation.Commands.ForwardToNewChain;
import f.drunky.Navigation.Commands.RemoveCurrent;
import f.drunky.Navigation.Names.ChainInfo;
import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

/**
 * Created by AZhloba on 12/6/2017.
 */

public class FRouter extends BaseRouter {
    private ChainInfo _curChainInfo;

    public ChainInfo getCurrentChainInfo() { return _curChainInfo; }
    public void setCurrentChainInfo(ChainInfo chainInfo) { _curChainInfo = chainInfo; }


    public boolean isChain(ChainInfo chain) { return chain.chain.equals(_curChainInfo.chain); }

    public void startNewChain(ChainInfo chainInfo) {
        _curChainInfo = chainInfo;

        executeCommand(new RemoveCurrent());
        executeCommand(new BackTo(null));
        executeCommand(new Replace(chainInfo.startView, null));
    }

    public void navigateToNewChain(ChainInfo chainInfo) {
        _curChainInfo = chainInfo;
        executeCommand(new ForwardToNewChain(chainInfo.startView, null));
    }

    public void navigateTo(String screenKey) {
        executeCommand(new Forward(screenKey, null));
    }


    public void showSystemMessage(String message) {
        executeCommand(new SystemMessage(message));
    }
}
