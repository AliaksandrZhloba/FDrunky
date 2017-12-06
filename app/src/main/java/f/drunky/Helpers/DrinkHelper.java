package f.drunky.Helpers;

import android.graphics.Color;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrinkAppearance;

/**
 * Created by AZhloba on 9/28/2017.
 */

public class DrinkHelper {
    public static ArrayList<Drink> GetDrinks() {
        ArrayList<Drink> drinks = new ArrayList<Drink>();

        /*DrinkAppearance wineAppearance = new DrinkAppearance(Color.parseColor("#B71C1C"), Color.parseColor("#D32F2F"), Color.WHITE, Color.parseColor("#B71C1C"));
        drinks.add(new Drink(0, "wine", 7, "Medium dry red wine", "$29.95", wineAppearance));
        drinks.add(new Drink(2, "wine", 7, "Medium dry wine", "$29.95", wineAppearance));
        drinks.add(new Drink(3, "wine", 7, "White wine", "$29.95", wineAppearance));
        drinks.add(new Drink(1, "wine",7, "Soft red wine", "$29.95", wineAppearance));

        DrinkAppearance vodkaAppearance = new DrinkAppearance(Color.parseColor("#BDBDBD"), Color.parseColor("#EEEEEE"), Color.BLACK, Color.parseColor("#BDBDBD"));
        drinks.add(new Drink(11, "vodka", 40, "Stolichnaya","$9.95", vodkaAppearance));
        drinks.add(new Drink(12, "vodka", 40, "Absolut", "$28.0", vodkaAppearance));

        DrinkAppearance ginAppearance = new DrinkAppearance(Color.parseColor("#0288D1"), Color.parseColor("#03A9F4"), Color.WHITE, Color.parseColor("#0288D1"));
        drinks.add(new Drink(21, "gin", 40, "Sapphire Bombay", "$29.95", ginAppearance));

        DrinkAppearance whiskeyAppearance = new DrinkAppearance(Color.YELLOW, Color.YELLOW, Color.WHITE, Color.YELLOW);
        drinks.add(new Drink(31, "whiskey", 40, "Jim Beam","$199.95", whiskeyAppearance));
        drinks.add(new Drink(32, "whiskey", 40, "Jonie Walker","$45", whiskeyAppearance));
        drinks.add(new Drink(33, "whiskey", 40, "Jack Daniels","$120.5", whiskeyAppearance));*/


        return drinks;
    }

    public static List<String> GetCategories() {
        return Arrays.asList("wine", "beer", "vodka", "gin", "whisky");
    }

    public static List<Drink> FindDrinks(String input, List<String> categories, List<Drink> drinks) {
        ArrayList<Drink> result = new ArrayList<Drink>();
        for (Drink drink:drinks) {
            if (drink.getTitle().toUpperCase().contains(input.toUpperCase()))
                result.add(drink);
        }

        if (result.size() > 0) {
            return result;
        }

        ArrayList<String> tmp = new ArrayList<String>();
        for (String category:categories) {
            if (category.toUpperCase().contains(input.toUpperCase()))
                tmp.add(category);
        }

        if (tmp.size() != 0) {
            for (String category : tmp) {
                for (Drink drink : drinks) {
                    if (drink.getCategory().equalsIgnoreCase(category))
                        result.add(drink);
                }
            }
        }

        return result;
    }
}
