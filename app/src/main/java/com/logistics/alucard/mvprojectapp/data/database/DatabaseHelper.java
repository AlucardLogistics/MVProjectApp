package com.logistics.alucard.mvprojectapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.logistics.alucard.mvprojectapp.data.IManagerData;

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseHelper {

    public static final String MYDATABASE = "myDataBase";
    public static final String TABLE = "userTable";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pass";
    public static final String EMAIL = "email";
    public static final int VERSION = 1;
    public static final String KEY_ID = "_id";

    public DatabaseHelper(Context context) {
        super(context, MYDATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + USERNAME + " TEXT," + PASSWORD + " TEXT,"
                + EMAIL + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //any upgrade can update the version and if newV > oldV then upgrade will be executed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    @Override
    public void createRow(IManagerData.OnResponseListener listener, String id) {
        listener.createRow("STuff");
    }

    @Override
    public void readRow(IManagerData.OnResponseListener listener, String id) {

    }

    @Override
    public void updateRow(IManagerData.OnResponseListener listener, String id) {

    }

    @Override
    public Integer deleteRow(IManagerData.OnResponseListener listener, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        listener.deleteRow(id);
        return db.delete(TABLE, "_id = ?", new String[] {id});
    }



}
