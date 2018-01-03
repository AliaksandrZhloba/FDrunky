package f.drunky.Helpers;

import java.util.Arrays;
import java.util.List;

import f.drunky.Entity.Gender;
import f.drunky.R;

/**
 * Created by AZhloba on 1/3/2018.
 */

public class GenderHelper {
    private static final Gender Empty = new Gender("", R.string.Gender);
    public static final Gender Male = new Gender("M", R.string.Male);
    public static final Gender Female = new Gender("F", R.string.Female);

    private static final List<Gender> _genders = Arrays.asList(Empty, Male, Female);


    public static List<Gender> getGenders() {
        return _genders;
    }
}
