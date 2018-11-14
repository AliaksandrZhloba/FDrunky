package f.drunky;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import f.drunky.Entity.Drink;
import f.drunky.Helpers.DrinkHelper;

import static org.junit.Assert.assertEquals;


public class DrinkHelperGetHintsTest {

    @Test
    public void one_word_category_1_isCorrect() {
        ArrayList<String> hints = DrinkHelper.getHints(GetCatalogue(), "Beer");
        assertEquals(hints.size(), 5);
    }

    @Test
    public void one_word_category_2_isCorrect() {
        ArrayList<String> hints = DrinkHelper.getHints(GetCatalogue(), "Wine");
        assertEquals(hints.size(), 9);
    }


    private HashMap<String, ArrayList<Drink>> GetCatalogue() {
        HashMap<String, ArrayList<Drink>> catalogue = new HashMap<>();
        catalogue.put("vodka", new ArrayList<Drink>(
                Arrays.asList(
                        new Drink(1, 40, "Vodka", null),
                        new Drink(1, 40, "Stolichnaya", null),
                        new Drink(1, 40, "Absolut", null),
                        new Drink(1, 40, "Finlandia", null),
                        new Drink(1, 40, "Ketel One", null),
                        new Drink(1, 40, "Chopin", null),
                        new Drink(1, 40, "Grey Goose", null),
                        new Drink(1, 40, "Van Gogh", null),
                        new Drink(1, 40, "Belvedere", null),
                        new Drink(1, 40, "Hangar 1", null),
                        new Drink(1, 40, "Three Olives", null)
                )));

        catalogue.put("whiskey", new ArrayList<Drink>(
                Arrays.asList(
                        new Drink(1, 40, "Whiskey", null),
                        new Drink(1, 40, "Bell's", null),
                        new Drink(1, 40, "Dewar's 12 Year Old", null),
                        new Drink(1, 40, "Black Velvet Reserve 8 Year Old", null),
                        new Drink(1, 40, "Jameson", null),
                        new Drink(1, 40, "Crown Royal Canadian Whisky", null),
                        new Drink(1, 40, "Canadian Club", null),
                        new Drink(1, 40, "Ballantine's Finest", null),
                        new Drink(1, 40, "Suntory Toki", null),
                        new Drink(1, 40, "Chivas Regal 12 Year Old", null),
                        new Drink(1, 40, "Johnnie Walker Red Label", null),
                        new Drink(1, 40, "Jack Daniel's Old No.7", null)
                )));

        catalogue.put("wine", new ArrayList<Drink>(
                Arrays.asList(
                        new Drink(1, 16, "Red sweet wine", null),
                        new Drink(1, 14, "Red semi-sweet wine", null),
                        new Drink(1, 12, "Red semi-dry wine", null),
                        new Drink(1, 10, "Red dry wine", null),
                        new Drink(1, 15, "White sweet wine", null),
                        new Drink(1, 13, "White semi-sweet wine", null),
                        new Drink(1, 11, "White semi-dry wine", null),
                        new Drink(1, 9, "White dry wine", null)
                )));

        catalogue.put("beer", new ArrayList<Drink>(
                Arrays.asList(
                        new Drink(1, 5, "Lager", null),
                        new Drink(1, 14, "Stout", null),
                        new Drink(1, 12, "Brown Ale", null),
                        new Drink(1, 10, "Pale Ale", null)
                )));

        return catalogue;
    }
}
