package f.drunky.Entity;

import android.graphics.Bitmap;

/**
 * Created by AZhloba on 9/27/2017.
 */

public class Drink {
    private int _id;
    private String _title;
    private String _price;
    private double _degree;
    private String _category;
    private DrinkAppearance _appearance;
    private Bitmap _bottlePicture;


    public int getId() {
        return _id;
    }
    public String getTitle() { return _title; }
    public String getPrice() { return _price; }
    public double getDegree() {
        return _degree;
    }
    public String getCategory() {
        return _category;
    }
    public Bitmap getBottlePicture() { return _bottlePicture; }

    public DrinkAppearance getAppearance() { return _appearance; }


    public Drink(int id, String category, double degree, String title, String price, DrinkAppearance appearance, Bitmap bottlePicture) {
        _id = id;
        _title = title;
        _price = price;
        _degree = degree;
        _category = category;
        _bottlePicture = bottlePicture;

        _appearance = appearance;
    }
}
