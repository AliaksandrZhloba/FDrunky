package f.drunky.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.FDrunkyApplication;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.ForwardToNewChain;
import f.drunky.Navigation.MenuController;
import f.drunky.Navigation.Names.ChainInfo;
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
    private ActionBarDrawerToggle _toggle;
    private NavigationView _navigationView;
    private TextView _txtHeader;


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
                ChainFragment chainFragment = (ChainFragment)fragment;
                chainFragment.setChainInfo(FDrunkyApplication.INSTANCE.getRouter().getCurrentChainInfo());
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
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }

        @Override
        public void applyCommand(Command command) {
            super.applyCommand(command);

            ChainInfo chain = FDrunkyApplication.INSTANCE.getRouter().getCurrentChainInfo();
            _navigationView.setCheckedItem(chain.menuId);
            _txtHeader.setText(chain.titleId);
        }
    };

    private MenuController menuController = new MenuController() {
        @Override
        public void enableMenu() {
            _navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            _toggle.setDrawerIndicatorEnabled(true);
        }

        @Override
        public void disableMenu() {
            _navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            _toggle.setDrawerIndicatorEnabled(false);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
        FDrunkyApplication.INSTANCE.setMenuController(menuController);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().removeNavigator();
        FDrunkyApplication.INSTANCE.removeMenuController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _flContent = findViewById(R.id.flContent);
        _navDrawer = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        _txtHeader = findViewById(R.id.txtHeader);

        //dirty fix
        FDrunkyApplication.INSTANCE.setMenuController(menuController);


        _toggle = new ActionBarDrawerToggle(
                this, _navDrawer, toolbar, 0, 0);
        _navDrawer.addDrawerListener(_toggle);
        _toggle.syncState();

        _navigationView = findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(item -> {
            _navDrawer.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_condition:
                    break;

                case R.id.nav_calculation:
                    presenter.gotoCalculation();
                    break;

                case R.id.nav_about:
                    presenter.gotoAbout();
                    break;

                // debug
                case R.id.nav_result:
                    FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.CALC_RESULT);
                    break;

                case R.id.nav_map:
                    FDrunkyApplication.INSTANCE.getRouter().navigateTo(Views.MAP);
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
            ChainInfo chain = ((ChainFragment)fragment).getChainInfo();
            FDrunkyApplication.INSTANCE.getRouter().setCurrentChainInfo(chain);
            _navigationView.setCheckedItem(chain.menuId);
            _txtHeader.setText(chain.titleId);
        }
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.flContent);
    }
}