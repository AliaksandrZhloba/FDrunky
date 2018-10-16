package f.drunky;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;

import f.drunky.Entity.State;
import f.drunky.Entity.Drink;
import f.drunky.Entity.DrinkAppearance;
import f.drunky.Entity.DrunkItem;
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
        public int Volume;

        public ArrayList<DrunkItem> DrunkList = new ArrayList<>();
        public State State = new State();
    }


    public SharedData SharedData = new SharedData();

    public static FDrunkyApplication INSTANCE;
    private Cicerone<FRouter> cicerone;
    private MenuController _menuController;


    @Override
    public void onCreate() {
        super.onCreate();


        /// DEBUG!!!!
        SharedData.DrinkEffect = DrinkEffect.ToRelax;
        SharedData.Drink = new Drink(1, "wine", 7, "Wine", "7.5",
                new DrinkAppearance( Color.RED,  Color.RED,  Color.RED,  Color.WHITE, Bitmap.createBitmap(1,1, Bitmap.Config.ALPHA_8)),
                Bitmap.createBitmap(1,1, Bitmap.Config.ALPHA_8));


        INSTANCE = this;
        cicerone = Cicerone.create(new FRouter());
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public FRouter getRouter() {
        return cicerone.getRouter();
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


    public LanguageController LanguageController;
}
