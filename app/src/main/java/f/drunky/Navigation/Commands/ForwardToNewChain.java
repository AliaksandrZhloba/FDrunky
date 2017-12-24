package f.drunky.Navigation.Commands;

import ru.terrakok.cicerone.commands.Forward;

/**
 * Created by AZhloba on 12/6/2017.
 */

public class ForwardToNewChain extends Forward {
    /**
     * Creates a {@link ForwardToNewChain} navigation command.
     *
     * @param screenKey      screen key
     * @param transitionData initial data
     */
    public ForwardToNewChain(String screenKey, Object transitionData) {
        super(screenKey, transitionData);
    }
}
