package f.drunky.Entity;

import android.graphics.Bitmap;

/**
 * Created by AZhloba on 9/27/2017.
 */

public class Drink {
    private int _id;
    private String _category;
    private String _title;
    private double _alcohol;
    private Bitmap _image;


    public int getId() {
        return _id;
    }
    public String getCategory() { return _category; }
    public String getTitle() { return _title; }
    public double getAlcohol() {
        return _alcohol;
    }
    public Bitmap getImage() { return _image; }


    public Drink(int id, double alcohol, String category, String title, Bitmap image) {
        _id = id;
        _category = category;
        _title = title;
        _alcohol = alcohol;
        _image = image;
    }
}
