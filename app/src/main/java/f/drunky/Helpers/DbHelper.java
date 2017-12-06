package f.drunky.Helpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import f.drunky.Entity.Drink;

/**
 * Created by AZhloba on 10/5/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static String DB_PATH;                  // полный путь к базе данных
    private static String DB_NAME = "fdrunky.db";
    private static final int SCHEMA = 1;            // версия базы данных

    public static final String DRINKS_TABLE_NAME = "Drinks";
    public static final String CATEGORIES_TABLE_NAME = "Categories";


    private Context _context;
    private SQLiteDatabase _dbase;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);

        _context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }


    public void createDataBase() {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }


    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
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
    private void copyDataBase() throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = _context.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

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


    public SQLiteDatabase openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //открываем БД
        _dbase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        return _dbase;
    }

    @Override
    public synchronized void close() {
        if(_dbase != null)
            _dbase.close();
        super.close();
    }
}
