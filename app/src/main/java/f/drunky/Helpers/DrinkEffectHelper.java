package f.drunky.Helpers;

import f.drunky.Entity.Drink;
import f.drunky.Types.DrinkEffect;

/**
 * Created by AZhloba on 10/3/2017.
 */

public class DrinkEffectHelper {
    public static String toString(DrinkEffect effect) {
        switch (effect){
            case ToRelax:
                return "To relax";

            case ToHaveAFun:
                return "To have a fun";

            case ToDrunkOver:
                return "To be legless";
        }

        return "?";
    }


    public static int calcVolume(DrinkEffect effect, Drink drink) {
        return 120;
    }

    public static String getAction1(DrinkEffect effect) {
        switch (effect){
            case ToRelax:
            case ToHaveAFun:
                return "don't drink";

            case ToDrunkOver:
                return "drink";
        }

        return "?";
    }

    public static String getAction2(DrinkEffect effect) {
        switch (effect){
            case ToRelax:
            case ToHaveAFun:
                return "more than";

            case ToDrunkOver:
                return "at least";
        }

        return "?";
    }
}
