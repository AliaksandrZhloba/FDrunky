package f.drunky;

import org.junit.Test;

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import f.drunky.Entity.DrunkItem;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.TimeHelper;
import f.drunky.mvp.models.UserProfileModel;

import static org.junit.Assert.assertEquals;

public class AcloModelUnitTest {
    private static final double DIF_DELTA = 0.0001;


    @Test
    public void emptyLog_isCorrect() throws Exception {
        UserProfileModel userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        assertEquals(0, AlcoHelper.calcMille(log, userProfile, TimeHelper.now()), DIF_DELTA);
    }

    @Test
    public void singleItem_isCorrect() throws Exception {
        UserProfileModel userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        Date useTime = new Date(0);
        log.add(new DrunkItem(useTime, null, null, 40, 100));

        assertEquals(0.530177515, AlcoHelper.calcMille(log, userProfile, useTime), DIF_DELTA);
        assertEquals(0.511844181, AlcoHelper.calcMille(log, userProfile, new Date(10 * 60 * 1000)), DIF_DELTA);
        assertEquals(0, AlcoHelper.calcMille(log, userProfile, new Date(290 * 60 * 1000)), DIF_DELTA);
    }

    @Test
    public void multiItem_isCorrect() throws Exception {
        UserProfileModel userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        log.add(new DrunkItem(new Date(0), null, null, 5, 250));
        log.add(new DrunkItem(new Date(30 * 60 * 1000), null, null, 5, 250));

        assertEquals(0.165680473, AlcoHelper.calcMille(log, userProfile, new Date(0)), DIF_DELTA);
        assertEquals(0.14734714, AlcoHelper.calcMille(log, userProfile, new Date(10 * 60 * 1000)), DIF_DELTA);
        assertEquals(0.276360947, AlcoHelper.calcMille(log, userProfile, new Date(30 * 60 * 1000)), DIF_DELTA);
        assertEquals(0.221360947, AlcoHelper.calcMille(log, userProfile, new Date(60 * 60 * 1000)), DIF_DELTA);
        assertEquals(0.111360947, AlcoHelper.calcMille(log, userProfile, new Date(120 * 60 * 1000)), DIF_DELTA);
        assertEquals(0, AlcoHelper.calcMille(log, userProfile, new Date(190 * 60 * 1000)), DIF_DELTA);
    }


    private UserProfileModel GetUnfilledUserProfile() {
        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.Gender = null;
        userProfileModel.Age = null;
        userProfileModel.Weight = null;

        return userProfileModel;
    }
}
