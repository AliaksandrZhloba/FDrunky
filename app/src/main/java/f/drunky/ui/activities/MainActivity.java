package f.drunky.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.LanguageController;
import f.drunky.Navigation.ChainFragment;
import f.drunky.Navigation.FNavigator;
import f.drunky.Navigation.Commands.ForwardToNewChain;
import f.drunky.Navigation.IBackHandler;
import f.drunky.Navigation.MenuController;
import f.drunky.Navigation.Names.ChainInfo;
import f.drunky.Navigation.Names.Views;
import f.drunky.R;
import f.drunky.mvp.presenters.MainPresenter;
import f.drunky.mvp.views.MainView;
import ru.terrakok.cicerone.Navigator;
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


    private Navigator navigator = new FNavigator(getSupportFragmentManager(), R.id.flContent) {
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
    private LanguageController languageController;

    @Override
    protected void onResume() {
        super.onResume();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
        FDrunkyApplication.INSTANCE.setMenuController(menuController);
        FDrunkyApplication.INSTANCE.LanguageController = languageController;
    }

    @Override
    protected void onPause() {
        super.onPause();
        FDrunkyApplication.INSTANCE.getNavigatorHolder().removeNavigator();
        FDrunkyApplication.INSTANCE.removeMenuController();
        FDrunkyApplication.INSTANCE.LanguageController = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsHelper.init();

        languageController = new LanguageController() {
            @Override
            public void switchLanguage(String language) {
                if (!language.equals(getLanguage())) {
                    setLanguage(language);
                    finish();

                    Intent refreshIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refreshIntent);
                }
            }
        };

        String language = SettingsHelper.getLanguage();
        switchLanguage(language);

        setContentView(R.layout.activity_main);

        _flContent = findViewById(R.id.flContent);
        _navDrawer = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        _txtHeader = findViewById(R.id.txtHeader);

        //dirty fix
        FDrunkyApplication.INSTANCE.setMenuController(menuController);
        FDrunkyApplication.INSTANCE.LanguageController = languageController;

        _toggle = new ActionBarDrawerToggle(
                this, _navDrawer, toolbar, 0, 0);
        _navDrawer.addDrawerListener(_toggle);
        _toggle.syncState();

        _navigationView = findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(item -> {
            _navDrawer.closeDrawer(GravityCompat.START);
            presenter.navigate(item.getItemId());
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (_navDrawer.isDrawerOpen(GravityCompat.START)) {
            _navDrawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment fragment = getCurrentFragment();
            if (fragment instanceof IBackHandler) {
                IBackHandler backHandler = (IBackHandler)fragment;
                backHandler.onBackPressed();
            }
            else {
                super.onBackPressed();

                ChainInfo chain = ((ChainFragment) getCurrentFragment()).getChainInfo();
                FDrunkyApplication.INSTANCE.getRouter().setCurrentChainInfo(chain);

                _navigationView.setCheckedItem(chain.menuId);
                _txtHeader.setText(chain.titleId);
            }
        }
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.flContent);
    }


    private void switchLanguage(String language) {
        Locale mylocale = new Locale(language);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.locale = mylocale;
        resources.updateConfiguration(conf, dm);
    }
}