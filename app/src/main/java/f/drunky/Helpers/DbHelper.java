package f.drunky.Helpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AZhloba on 10/5/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static String DB_PATH;                  // полный путь к базе данных
    private static String DRINKS_DB_NAME = "fdrunky-drinks.db";
    private static String LOG_DB_NAME = "fdrunky-log.db";
    private static final int SCHEMA = 1;            // версия базы данных

    public static final String DRINKS_TABLE_NAME = "Drinks";
    public static final String CATEGORIES_TABLE_NAME = "Categories";


    private Context _context;


    public DbHelper(Context context) {
        super(context, DRINKS_DB_NAME, null, SCHEMA);

        _context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }


    public void createDataBases() {
        boolean dbDrinksExist = checkDataBase(DRINKS_DB_NAME);
        if (!dbDrinksExist) {
            this.getReadableDatabase();

            try {
                copyDataBase(DRINKS_DB_NAME);
            } catch (IOException e) {
                throw new Error("Error copying Drinks database");
            }
        }

        boolean dbLogExist = checkDataBase(LOG_DB_NAME);
        if (!dbDrinksExist) {
            this.getReadableDatabase();

            try {
                copyDataBase(LOG_DB_NAME);
            } catch (IOException e) {
                throw new Error("Error copying Logs database");
            }
        }
    }


    private boolean checkDataBase(String dbName) {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + dbName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e){
            //база еще не существует
        }
        if (checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     **/
    private void copyDataBase(String dbName) throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = _context.getAssets().open(dbName);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + dbName;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public SQLiteDatabase openDrinksDataBase() throws SQLException {
        return openDataBase(DRINKS_DB_NAME);
    }

    public SQLiteDatabase openLogDataBase() throws SQLException {
        return openDataBase(LOG_DB_NAME);
    }

    private SQLiteDatabase openDataBase(String dbName) throws SQLException {
        String mPath = DB_PATH + dbName;
        //открываем БД
        return SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
