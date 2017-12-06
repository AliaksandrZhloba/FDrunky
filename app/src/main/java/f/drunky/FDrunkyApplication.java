package f.drunky;

import android.app.Application;

import f.drunky.Entity.Drink;
import f.drunky.Navigation.BackController;
import f.drunky.Navigation.FRouter;
import f.drunky.Navigation.MenuController;
import f.drunky.Types.DrinkEffect;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;

/**
 * Created by AZhloba on 11/23/2017.
 */

public class FDrunkyApplication extends Application {
    public class SharedData {
        public DrinkEffect DrinkEffect;
        public Drink Drink;
    }


    public SharedData SharedData = new SharedData();

    public static FDrunkyApplication INSTANCE;
    private Cicerone<FRouter> cicerone;
    private BackController _backController;
    private MenuController _menuController;


    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        cicerone = Cicerone.create(new FRouter());
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public FRouter getRouter() {
        return cicerone.getRouter();
    }


    public void setBackController(BackController backNavigator) {
        _backController = backNavigator;
    }

    public BackController getBackController() {
        return _backController;
    }

    public void removeBackController() {
        _backController = null;
    }

    public void setMenuController(MenuController menuController) {
        _menuController = menuController;
    }

    public MenuController getMenuController() {
        return _menuController;
    }

    public void removeMenuController() {
        _menuController = null;
    }
}
