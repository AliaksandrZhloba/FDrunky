package f.drunky.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;

/**
 * Created by AZhloba on 9/28/2017.
 */

public class DrinkHelper {
    public static List<Drink> findDrinks(HashMap<String, ArrayList<Drink>> catalog, String input) {

        ArrayList<Drink> result = new ArrayList<>();


        ArrayList<Drink> drinks = new ArrayList<>();
        for (ArrayList<Drink> drinkSet : catalog.values()) {
            drinks.addAll(drinkSet);
        }

        for (Drink drink : drinks) {
            if (drink.getTitle().toUpperCase().contains(input.toUpperCase()))
                result.add(drink);
        }

        if (result.size() > 0) {
            return result;
        }

        Set<String> categories = catalog.keySet();
        ArrayList<String> suitableСategories = new ArrayList<>();
        for (String category : categories) {
            if (category.toUpperCase().contains(input.toUpperCase())) {
                suitableСategories.add(category);
            }
        }

        if (suitableСategories.size() > 0) {
            for (String category : suitableСategories) {
                result.addAll(catalog.get(category));
            }
        }

        return result;
    }
}
