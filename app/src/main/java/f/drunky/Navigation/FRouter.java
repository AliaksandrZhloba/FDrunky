package f.drunky.Navigation;

import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Created by AZhloba on 12/6/2017.
 */

public class FRouter extends BaseRouter {
    private String _curChain;

    public String getCurrentChain() { return _curChain; }
    public void setCurrentChain(String chain) { _curChain = chain; }

    public void startNewChain(String chain, String screenKey) {
        _curChain = chain;
        executeCommand(new BackTo(null));
        executeCommand(new Replace(screenKey, null));
    }

    public void navigateToNewChain(String chain, String screenKey) {
        _curChain = chain;
        executeCommand(new ForwardToNewChain(screenKey, null));
    }

    public void navigateTo(String screenKey) {
        executeCommand(new Forward(screenKey, null));
    }
}
