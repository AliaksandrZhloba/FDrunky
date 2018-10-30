package f.drunky.mvp.models;

/**
 * Created by AZhloba on 1/3/2018.
 */

public class UserProfile {
    public String Gender;
    public Integer Age;
    public Float Weight;

    public boolean isFilled() {
        return Gender != null && Age != null && Weight != null;
    }
}
