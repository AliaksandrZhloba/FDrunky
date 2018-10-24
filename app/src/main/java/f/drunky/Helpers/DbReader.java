package f.drunky.Helpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrunkItem;
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

        SQLiteDatabase dbDrinks = _dbHelper.openDrinksDataBase();

        sql = "SELECT Id, Category, Title, Alcohol, Image, Info FROM Drinks";
        mCur = dbDrinks.rawQuery(sql, null);
        if (mCur.moveToFirst()) {
            do {
                int id = mCur.getInt(0);
                String category = mCur.getString(1);
                String title = mCur.getString(2);
                float alcohol = mCur.getFloat(3);
                byte[] imgByte = mCur.getBlob(4);
                Bitmap img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                String info = mCur.getString(5);

                Drink drink = new Drink(id, alcohol, title, img);
                String[] categories = category.split(",");
                for (String cat : categories) {
                    if (!FDrunkyApplication.INSTANCE.SharedData.Catalog.containsKey(cat)) {
                        FDrunkyApplication.INSTANCE.SharedData.Categories.add(cat);
                        FDrunkyApplication.INSTANCE.SharedData.Catalog.put(cat, new ArrayList<>());
                    }

                    FDrunkyApplication.INSTANCE.SharedData.Catalog.get(cat).add(drink);
                }

                FDrunkyApplication.INSTANCE.SharedData.Drinks.add(drink);
            } while (mCur.moveToNext());
        }

        dbDrinks.close();
    }

    public static void loadLog() {
        String sql;
        Cursor mCur;

        SQLiteDatabase dbLog = _dbHelper.openLogDataBase();

        sql = "SELECT UseTime, Drink, Image, Alcohol, Volume FROM Log";
        mCur = dbLog.rawQuery(sql, null);
        if (mCur.moveToFirst())
        {
            {
                String useTime = mCur.getString(0);
                String drink = mCur.getString(1);
                byte[] imgByte = mCur.getBlob(2);
                Bitmap img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                double alcohol = mCur.getDouble(3);
                int volume = mCur.getInt(4);

                try {
                    DrunkItem item = new DrunkItem(DateHelper.FromString(useTime), drink, img, alcohol, volume);
                    FDrunkyApplication.INSTANCE.SharedData.DrunkList.add(item);
                }
                catch (ParseException e)
                { }
            } while (mCur.moveToNext());
        }

        dbLog.close();
    }
}
