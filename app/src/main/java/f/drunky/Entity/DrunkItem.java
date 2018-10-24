package f.drunky.Entity;

import android.graphics.Bitmap;

import java.util.Date;

import f.drunky.R;


public class DrunkItem {
    private Date _useTime;
    private String _drink;
    private Bitmap _image;
    private double _alcohol;
    private int _volume;

    public Date getUseTime() {
        return _useTime;
    }

    public String getDrink() {
        return _drink;
    }

    public Bitmap getImage() {
        return _image;
    }

    public double getAlcohol() {
        return _alcohol;
    }

    public int getVolume() {
        return _volume;
    }

    public DrunkItem(Date useTime, String drink, Bitmap image, double alcohol, int volume) {
        _useTime = useTime;
        _drink = drink;
        _image = image;
        _alcohol = alcohol;
        _volume = volume;
    }
}
