package f.drunky.Entity;

/**
 * Created by AZhloba on 12/4/2017.
 */

public class DrinkCategory {
    private int _id;
    private String _title;
    private DrinkAppearance _appearance;


    public int getId() { return _id; }
    public String getTitle() { return _title; }
    public DrinkAppearance getDrinkAppearance() { return _appearance; }


    public DrinkCategory(int id, String title, DrinkAppearance appearance) {
        _id  = id;
        _title = title;
        _appearance = appearance;
    }
}
