package f.drunky.Entity;

import java.util.Date;

import f.drunky.R;


public class DrunkItem {
    private Date _useTime;
    private Drink _drink;
    private int _volume;

    public Date getUseTime() {
        return _useTime;
    }

    public Drink getDrink() {
        return _drink;
    }

    public int getVolume() {
        return _volume;
    }


    public DrunkItem(Date useTime, Drink drink, int volume) {
        _useTime = useTime;
        _drink = drink;
        _volume = volume;
    }
}