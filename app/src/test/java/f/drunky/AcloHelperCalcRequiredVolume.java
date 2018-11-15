package f.drunky;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.TimeHelper;
import f.drunky.Types.DrinkEffect;
import f.drunky.mvp.models.UserProfile;

import static org.junit.Assert.assertEquals;

public class AcloHelperCalcRequiredVolume {
    private static final double DIF_DELTA = 0.0001;


    @Test
    public void emptyLog_oneDrink() {
        UserProfile userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = GetEmptyDrunkLog();

        double vodkaVolume = AlcoHelper.calcRequiredVolume(log, userProfile, TimeHelper.now(), DrinkEffect.ToHaveAFun, new Drink(1, 40, "Vodka", null));
        assertEquals(226, vodkaVolume, DIF_DELTA);

        double beerVolume = AlcoHelper.calcRequiredVolume(log, userProfile, TimeHelper.now(), DrinkEffect.ToRelax, new Drink(1, 5, "Beer", null));
        assertEquals(603, beerVolume, DIF_DELTA);

        double wineVolume = AlcoHelper.calcRequiredVolume(log, userProfile, TimeHelper.now(), DrinkEffect.ToDrunkOver, new Drink(1, 16, "Wine", null));
        assertEquals(848, wineVolume, DIF_DELTA);
    }


    private UserProfile GetUnfilledUserProfile() {
        UserProfile userProfileModel = new UserProfile();
        userProfileModel.Gender = null;
        userProfileModel.Age = null;
        userProfileModel.Weight = null;

        return userProfileModel;
    }

    private ArrayList<DrunkItem> GetEmptyDrunkLog() {
        return new ArrayList<>();
    }
}
