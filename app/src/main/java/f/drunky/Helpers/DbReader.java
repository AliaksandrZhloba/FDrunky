package f.drunky.Helpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import f.drunky.Entity.Drink;
import f.drunky.Entity.DrunkItem;
import f.drunky.Entity.State;
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

        String lang = FDrunkyApplication.INSTANCE.LanguageController.getLanguage();
        String categoryColumn = "Category" + lang;
        String titleColumn = "Title" + lang;
        sql = "SELECT Id, " + categoryColumn +", " + titleColumn + ", Alcohol, Image, Info FROM Drinks";
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

                Drink drink = new Drink(id, alcohol, category, title, img);
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

    public static void getLog() {
        int lastEvent = getLastEvent();

        if (lastEvent > 0) {
            ArrayList<DrunkItem> lastEventLog = getLog(lastEvent);
            State curState = AlcoHelper.calcState(lastEventLog, FDrunkyApplication.INSTANCE.SharedData.UserProfile, TimeHelper.now());
            if (curState.Bac > 0) {
                FDrunkyApplication.INSTANCE.SharedData.Event = lastEvent;
                FDrunkyApplication.INSTANCE.SharedData.State = curState;
                FDrunkyApplication.INSTANCE.SharedData.DrunkList = lastEventLog;

                return;
            }
        }

        FDrunkyApplication.INSTANCE.SharedData.Event = lastEvent + 1;
        FDrunkyApplication.INSTANCE.SharedData.State = State.Sober;
        FDrunkyApplication.INSTANCE.SharedData.DrunkList.clear();
    }

    public static int getLastEvent() {
        SQLiteDatabase dbLog = _dbHelper.openLogDataBase();

        int lastEvent = 0;

        String sql = "SELECT Event FROM Log";
        Cursor mCur = dbLog.rawQuery(sql, null);
        if (mCur.moveToLast()) {
            lastEvent = mCur.getInt(0);
        }

        dbLog.close();

        return lastEvent;
    }

    public static ArrayList<DrunkItem> getLog(int event) {
        SQLiteDatabase dbLog = _dbHelper.openLogDataBase();

        ArrayList<DrunkItem> items = new ArrayList<>();

        String sql = "SELECT UseTime, Drink, Image, Alcohol, Volume FROM Log WHERE Event =?";
        Cursor mCur = dbLog.rawQuery(sql, new String[] { Integer.toString(event) });
        if (mCur.moveToFirst())
        {
            do {
                String useTime = mCur.getString(0);
                String drink = mCur.getString(1);
                byte[] imgByte = mCur.getBlob(2);
                Bitmap img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                double alcohol = mCur.getDouble(3);
                int volume = mCur.getInt(4);

                try {
                    DrunkItem item = new DrunkItem(event, DateHelper.fromString(useTime), drink, img, alcohol, volume);
                    items.add(0, item);
                }
                catch (ParseException e)
                { }
            } while (mCur.moveToNext());
        }

        Collections.sort(items, (o1, o2) -> o2.getUseTime().compareTo(o1.getUseTime()));
        dbLog.close();

        return items;
    }


    public static void saveLogItem(DrunkItem item) {
        SQLiteDatabase dbLog = _dbHelper.openLogDataBase();

        ContentValues values = new ContentValues();
        values.put("Event", item.getEvent());
        values.put("UseTime", DateHelper.toString(item.getUseTime()));
        values.put("Drink", item.getDrink());
        values.put("Image", BitmapHelper.getBytes(item.getImage()));
        values.put("Alcohol", item.getAlcohol());
        values.put("Volume", item.getVolume());

        dbLog.insert("Log", null, values);
        dbLog.close();
    }

    public static void deleteLogItem(DrunkItem item) {
        SQLiteDatabase dbLog = _dbHelper.openLogDataBase();
        dbLog.delete("Log", "UseTime =?", new String[] { DateHelper.toString(item.getUseTime()) });
        dbLog.close();
    }
}
