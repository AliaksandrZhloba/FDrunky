package f.drunky.Navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import f.drunky.Navigation.Commands.RemoveCurrent;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;

/**
 * Created by AZhloba on 12/24/2017.
 */

public abstract class FNavigator extends SupportFragmentNavigator {

    private FragmentManager fragmentManager;
    private int containerId;

    public FNavigator(FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);

        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    @Override
    public void applyCommand(Command command) {
        if (command instanceof RemoveCurrent) {
            Fragment currentFragment = getVisibleFragment();
            if (currentFragment != null) {
                fragmentManager.beginTransaction().remove(currentFragment).setCustomAnimations(0, 0, 0, 0).commit();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        else {
            super.applyCommand(command);
        }
    }


    private Fragment getVisibleFragment(){
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
}
