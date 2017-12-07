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
