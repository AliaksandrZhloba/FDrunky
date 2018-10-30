package f.drunky;

import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;

import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
import f.drunky.Helpers.AlcoHelper;
import f.drunky.Helpers.TimeHelper;
import f.drunky.mvp.models.UserProfile;

import static org.junit.Assert.assertEquals;

public class AcloModelUnitTest {
    private static final double DIF_DELTA = 0.0001;


    @Test
    public void emptyLog_isCorrect() throws Exception {
        UserProfile userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        State state = AlcoHelper.calcState(log, userProfile, TimeHelper.now());
        assertEquals(0, state.Mille, DIF_DELTA);
        assertEquals(0, state.CanDriveInHours, DIF_DELTA);
    }

    @Test
    public void singleItem_isCorrect() throws Exception {
        UserProfile userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        Date useTime = new Date(0);
        log.add(new DrunkItem(useTime, null, null, 40, 100));

        State state;

        state = AlcoHelper.calcState(log, userProfile, useTime);
        assertEquals(0.530177515, state.Mille, DIF_DELTA);
        assertEquals(4.819795589, state.CanDriveInHours, DIF_DELTA);

        state = AlcoHelper.calcState(log, userProfile, new Date(10 * 60 * 1000));
        assertEquals(0.511844181, state.Mille, DIF_DELTA);
        assertEquals(4.653128922, state.CanDriveInHours, DIF_DELTA);

        state = AlcoHelper.calcState(log, userProfile, new Date(290 * 60 * 1000));
        assertEquals(0, state.Mille, DIF_DELTA);
        assertEquals(0, state.CanDriveInHours, DIF_DELTA);
    }

    @Test
    public void multiItem_isCorrect() throws Exception {
        UserProfile userProfile = GetUnfilledUserProfile();
        ArrayList<DrunkItem> log = new ArrayList<>();

        log.add(new DrunkItem(new Date(0), null, null, 5, 250));
        log.add(new DrunkItem(new Date(30 * 60 * 1000), null, null, 5, 250));

        assertEquals(0.165680473, AlcoHelper.calcState(log, userProfile, new Date(0)).Mille, DIF_DELTA);
        assertEquals(0.14734714, AlcoHelper.calcState(log, userProfile, new Date(10 * 60 * 1000)).Mille, DIF_DELTA);
        assertEquals(0.276360947, AlcoHelper.calcState(log, userProfile, new Date(30 * 60 * 1000)).Mille, DIF_DELTA);
        assertEquals(0.221360947, AlcoHelper.calcState(log, userProfile, new Date(60 * 60 * 1000)).Mille, DIF_DELTA);
        assertEquals(0.111360947, AlcoHelper.calcState(log, userProfile, new Date(120 * 60 * 1000)).Mille, DIF_DELTA);
        assertEquals(0, AlcoHelper.calcState(log, userProfile, new Date(190 * 60 * 1000)).Mille, DIF_DELTA);
    }


    private UserProfile GetUnfilledUserProfile() {
        UserProfile userProfileModel = new UserProfile();
        userProfileModel.Gender = null;
        userProfileModel.Age = null;
        userProfileModel.Weight = null;

        return userProfileModel;
    }
}
