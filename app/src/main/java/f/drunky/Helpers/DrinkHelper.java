package f.drunky.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    public static List<Integer> getVolumes(Drink drink) {
        switch (drink.getCategory().toLowerCase()) {
            case "vodka":
            case "whiskey":
            case "brandy":
            case "cognac":
            case "brandy, cognac":
            case "rum":
            case "tequila":
            case "водка":
            case "виски":
            case "бренди":
            case "коньяк":
            case "бренди, коньяк":
            case "ром":
            case "текила":
                return Arrays.asList(50, 100, 150, 200, 250, 350, 400);
            case "wine":
            case "вино":
                return Arrays.asList(100, 150, 200, 300, 500);
            case "beer":
            case "пиво":
                return Arrays.asList(250, 300, 500, 1000);
            case "cocktail":
            case "коктейль":
                return Arrays.asList(100, 150, 200, 300, 500);
        }

        return null;
    }
}
