package f.drunky.Helpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrinkAppearance;
import f.drunky.Entity.DrinkCategory;

/**
 * Created by AZhloba on 10/5/2017.
 */


public class DbReader {
    private static DbHelper _dbHelper;

    private static ArrayList<DrinkCategory> _categories;
    private static ArrayList<Drink> _drinks;


    public static ArrayList<Drink> getDrinks() { return _drinks; }
    public static ArrayList<DrinkCategory> getDrinkCategories() { return _categories; }


    public static void init(DbHelper dbHelper) {
        _dbHelper = dbHelper;
    }


    public static List<String> LoadCategories() {
        return DrinkHelper.GetCategories();
    }

    public static void loadDrinks() {
        String sql;
        Cursor mCur;

        _categories = new ArrayList<>();
        _drinks = new ArrayList<>();

        SQLiteDatabase db = _dbHelper.openDataBase();

        sql = "SELECT Id, Title, ButtonColor, CaptionColor, StatusbarColor, TextColor, GlassImage FROM Categories";
        mCur = db.rawQuery(sql, null);
        mCur.moveToFirst();
        do
        {
            int id = mCur.getInt(0);
            String title = mCur.getString(1);
            int buttonColor = Color.parseColor(mCur.getString(2));
            int captionColor = Color.parseColor(mCur.getString(3));
            int statusbarColor = Color.parseColor(mCur.getString(4));
            int textColor = Color.parseColor(mCur.getString(5));
            byte[] imgByte = mCur.getBlob(6);
            Bitmap glassPicture = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);

            DrinkAppearance appearance = new DrinkAppearance(buttonColor, captionColor, statusbarColor, textColor, glassPicture);
            DrinkCategory category = new DrinkCategory(id, title, appearance);
            _categories.add(category);
        } while (mCur.moveToNext());


        sql = "SELECT Id, Title, Price, Degree, Category_Id, Url, Image  FROM Drinks";
        mCur = db.rawQuery(sql, null);
        mCur.moveToFirst();
        do
        {
            int id = mCur.getInt(0);
            String title = mCur.getString(1);
            String price = mCur.getString(2);
            float degree = mCur.getFloat(3);
            int category_Id = mCur.getInt(4);
            String url = mCur.getString(5);
            byte[] imgByte = mCur.getBlob(6);
            Bitmap img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);

            DrinkCategory category = findCategory(category_Id);
            // TODO: change signature
            Drink drink = new Drink(id, category.getTitle(), degree, title, price, category.getDrinkAppearance(), img);
            _drinks.add(drink);
        } while (mCur.moveToNext());
    }

    private static DrinkCategory findCategory(int category_id) {
        for (DrinkCategory category:_categories) {
            if (category.getId() == category_id) {
                return category;
            }
        }

        return null;
    }
}
