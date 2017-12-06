package f.drunky.Entity;

import android.graphics.Bitmap;

/**
 * Created by AZhloba on 10/3/2017.
 */

public class DrinkAppearance {
    private int _backgroundColor;
    private int _captionBackgroundColor;
    private int _statusBarColor;
    private int _textColor;
    private Bitmap _glassPicture;

    public int getBackgroundColor() { return _backgroundColor; }
    public int getCaptionBackgroundColor() { return _captionBackgroundColor; }
    public int getStatusBarColor() { return _statusBarColor; }
    public int getTextColor() { return _textColor; }
    public Bitmap getGlassPicture() { return _glassPicture; }


    public DrinkAppearance(int buttonBackgroundColor, int captionBackgroundColor, int statusBarColor, int textColor, Bitmap glassPicture) {
        _backgroundColor = buttonBackgroundColor;
        _captionBackgroundColor = captionBackgroundColor;
        _statusBarColor = statusBarColor;
        _textColor = textColor;
        _glassPicture = glassPicture;
    }
}
