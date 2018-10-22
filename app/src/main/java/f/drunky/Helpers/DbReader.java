package f.drunky.Helpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import f.drunky.Entity.Drink;
import f.drunky.FDrunkyApplication;

/**
 * Created by AZhloba on 10/5/2017.
 */


public class DbReader {
    private static DbHelper _dbHelper;

    public static void init(DbHelper dbHelper) {
        _dbHelper = dbHelper;
    }


    public static void loadDrinks() {
        String sql;
        Cursor mCur;

        SQLiteDatabase db = _dbHelper.openDataBase();

        sql = "SELECT Id, Category, Title, Alcohol, Image, Info FROM Drinks";
        mCur = db.rawQuery(sql, null);
        mCur.moveToFirst();
        do
        {
            int id = mCur.getInt(0);
            String category = mCur.getString(1);
            String title = mCur.getString(2);
            float alcohol = mCur.getFloat(3);
            byte[] imgByte = mCur.getBlob(4);
            Bitmap img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            String info = mCur.getString(5);

            Drink drink = new Drink(id, alcohol, title, img);
            String[] categories = category.split(",");
            for (String cat: categories) {
                if (!FDrunkyApplication.INSTANCE.SharedData.Catalog.containsKey(cat)) {
                    FDrunkyApplication.INSTANCE.SharedData.Categories.add(cat);
                    FDrunkyApplication.INSTANCE.SharedData.Catalog.put(cat, new ArrayList<>());
                }

                FDrunkyApplication.INSTANCE.SharedData.Catalog.get(cat).add(drink);
            }

            FDrunkyApplication.INSTANCE.SharedData.Drinks.add(drink);
        } while (mCur.moveToNext());
    }
}
