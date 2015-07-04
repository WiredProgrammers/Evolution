package yuvaunstoppable.evolution.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import yuvaunstoppable.evolution.User;

/**
 * Created by Yash on 11-Jun-15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Evolution.db";
    public static final String TABLE_USERS = "users";

    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_TYPE = "type";
    public static final String COLUMN_USERS_NAME = "name";

    public static DBHelper instance = null;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static DBHelper getInstance(Context context){
        if(instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "("+COLUMN_USERS_USERNAME +" TEXT,"
                +COLUMN_USERS_PASSWORD+" TEXT,"+COLUMN_USERS_TYPE+" TEXT,"+COLUMN_USERS_NAME
                +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public User login() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from `" + TABLE_USERS + "`", null);
        res.moveToFirst();
        if(res.getCount()>0)
            return new User(res.getString(0),res.getString(2),res.getString(3));
        else return null;
    }

    public void addLogin(String username, String password, String type, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERS_USERNAME, username);
        contentValues.put(COLUMN_USERS_PASSWORD, password);
        contentValues.put(COLUMN_USERS_TYPE, type);
        contentValues.put(COLUMN_USERS_NAME, name);
        db.insert(TABLE_USERS, null, contentValues);
    }

    public void logout() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }

}
