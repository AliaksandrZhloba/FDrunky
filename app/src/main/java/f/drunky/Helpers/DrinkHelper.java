package f.drunky.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import f.drunky.Entity.Drink;

/**
 * Created by AZhloba on 9/28/2017.
 */

public class DrinkHelper {

    public static ArrayList<Drink> findDrinks(HashMap<String, ArrayList<Drink>> catalog, String input) {

        ArrayList<Drink> drinks = new ArrayList<>();
        for (ArrayList<Drink> drinkSet : catalog.values()) {
            drinks.addAll(drinkSet);
        }

        Set<String> categories = catalog.keySet();


        ArrayList<Drink> result = new ArrayList<>();

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

        for (Drink drink : drinks) {
            if (!result.contains(drink) && drink.getTitle().toUpperCase().contains(input.toUpperCase()))
                result.add(drink);
        }

        if (result.size() > 0) {
            return result;
        }

        String[] inputWords = input.split(" ");
        for (int i = 0; i < inputWords.length; i++) {
            inputWords[i] = inputWords[i].toUpperCase();
        }

        for (Drink drink : drinks) {
            String drinkTitle = drink.getTitle().toUpperCase();
            Boolean containsAllWords = true;
            for (String word : inputWords) {
                if (!drinkTitle.contains(word)) {
                    containsAllWords = false;
                    break;
                }
            }

            if (containsAllWords) {
                if (!result.contains(drink))
                    result.add(drink);
            }
        }

        return result;
    }

    public static ArrayList<String> getHints(HashMap<String, ArrayList<Drink>> catalog, String input) {

        ArrayList<Drink> drinks = new ArrayList<>();
        for (ArrayList<Drink> drinkSet : catalog.values()) {
            drinks.addAll(drinkSet);
        }

        Set<String> categories = catalog.keySet();


        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> suitableСategories = new ArrayList<>();
        for (String category : categories) {
            if (category.toUpperCase().contains(input.toUpperCase())) {
                suitableСategories.add(category);
                result.add(category);
            }
        }

        ArrayList<Drink> sutableDrinks = findDrinks(catalog, input);
        for (Drink drink : sutableDrinks) {
            result.add(drink.getTitle());
        }

        return result;
    }
}
