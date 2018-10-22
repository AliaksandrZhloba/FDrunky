package f.drunky.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;

/**
 * Created by AZhloba on 9/28/2017.
 */

public class DrinkHelper {
    public static List<Drink> FindDrinks(String input) {
        ArrayList<Drink> result = new ArrayList<>();
        for (Drink drink:FDrunkyApplication.INSTANCE.SharedData.Drinks) {
            if (drink.getTitle().toUpperCase().contains(input.toUpperCase()))
                result.add(drink);
        }

        if (result.size() > 0) {
            return result;
        }

        ArrayList<String> categories = new ArrayList<>();
        for (String category:FDrunkyApplication.INSTANCE.SharedData.Categories) {
            if (category.toUpperCase().contains(input.toUpperCase()))
                categories.add(category);
        }

        if (categories.size() > 0) {
            for (String category : categories) {
                result.addAll(FDrunkyApplication.INSTANCE.SharedData.Catalog.get(category));
            }
        }

        return result;
    }
}
