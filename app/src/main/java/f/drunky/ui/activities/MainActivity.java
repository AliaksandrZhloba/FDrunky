package f.drunky.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.BackController;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.ForwardToNewChain;
import f.drunky.Navigation.MenuController;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.mvp.presenters.MainPresenter;
import f.drunky.mvp.views.MainView;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Created by AZhloba on 10/30/2017.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @InjectPresenter
    MainPresenter presenter;

    private FrameLayout _flContent;
    private DrawerLayout _navDrawer;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.flContent) {
        @Override
        protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
            if (command instanceof ForwardToNewChain ||
                command instanceof Replace) {
                fragmentTransaction.setCustomAnimations(
                        R.anim.enter, R.anim.exit,
                        R.anim.enter, R.anim.exit);
            }
            else if (command instanceof Forward) {
                fragmentTransaction.setCustomAnimations(
                        R.anim.slide_from_right, R.anim.slide_to_left,
                        R.anim.slide_from_left, R.anim.slide_to_right);
            }
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            try {
                Fragment fragment = Views.GetFragment(screenKey).newInstance();
                ((ChainFragment)fragment).chain = FDrunkyApplication.INSTANCE.getRouter().getCurrentChain();
                return fragment;
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } {}
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
            finish();
        }
    };

    private BackController backController = new BackController() {
        @Override
        public void goBack() {
            onBackPressed();
        }
    };

    private MenuController menuController = new MenuController() {
        @Override
        public void openMenu() {
            _navDrawer.openDrawer(GravityCompat.START);
        }

        @Override
        public void enableMenu() {
            _navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

        @Override
        public void disableMenu() {
            _navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
        FDrunkyApplication.INSTANCE.setMenuController(menuController);
        FDrunkyApplication.INSTANCE.setBackController(backController);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().removeNavigator();
        FDrunkyApplication.INSTANCE.removeMenuController();
        FDrunkyApplication.INSTANCE.removeBackController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dirty fix
        FDrunkyApplication.INSTANCE.setMenuController(menuController);

        _flContent = findViewById(R.id.flContent);
        _navDrawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            _navDrawer.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_about:
                    presenter.gotoAbout();
                    break;
            }

            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (_navDrawer.isDrawerOpen(GravityCompat.START)) {
            _navDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Fragment fragment = getCurrentFragment();
            ChainFragment chainFragment = (ChainFragment)fragment;
            FDrunkyApplication.INSTANCE.getRouter().setCurrentChain(chainFragment.chain);
        }
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.flContent);
    }
}
