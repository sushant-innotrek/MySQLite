package in.innotrek.www.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sushant Kamble on 4/3/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private String tableName = "mysqlite";

    public DatabaseHelper(Context context) {
        super(context,"mysqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +tableName+ "(username TEXT ,password TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP IF TABLE EXISTS " + tableName;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean addData(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = sqLiteDatabase.insert(tableName,null,contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+tableName;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
