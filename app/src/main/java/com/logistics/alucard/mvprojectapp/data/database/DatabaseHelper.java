package com.logistics.alucard.mvprojectapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.logistics.alucard.mvprojectapp.data.model.User;
import com.logistics.alucard.mvprojectapp.data.database.sqlite.SQLiteTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static final String MYDATABASE = "myDataBase";
//    public static final String TABLE = "userTable";
//    public static final String USERNAME = "user";
//    public static final String PASSWORD = "pass";
//    public static final String EMAIL = "email";
    public static final int VERSION = 1;
//    public static final String KEY_ID = "_id";

    public DatabaseHelper(Context context) {
        super(context, MYDATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
//                + KEY_ID + " INTEGER PRIMARY KEY," + USERNAME + " TEXT," + PASSWORD + " TEXT,"
//                + EMAIL + " TEXT" + ")";

        db.execSQL(SQLiteTable.DB_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //any upgrade can update the version and if newV > oldV then upgrade will be executed
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteTable.DB_USER);
        onCreate(db);
    }

    public boolean insertData(String table, ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, cv);
        if(result == -1) {
            Log.d(TAG, "insertData: failed to save data");
            return false;
        } else {
            Log.d(TAG, "insertData: save data succesfully");
        }   return true;
    }

    public int updateData(String table, ContentValues values,
                          String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(table, values,
                SQLiteTable.EMAIL + " =? ",
                new String[]{email});
    }

    public User getUserDetail(String email){
        User user;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SQLiteTable.USER_ID,
                SQLiteTable.USERNAME,
                SQLiteTable.PASSWORD,
                SQLiteTable.EMAIL
        };

        String selection = SQLiteTable.EMAIL + " =? " ;
        String[] args={email};

        Cursor cursor = db.query(SQLiteTable.TABLE, columns,
                selection, args, null, null, null);

        if (cursor != null && cursor.moveToFirst()){

            user = new User();
            user.setUser_id(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));

            return user;
        }

        return null;
    }

    public boolean  checkUser(String email, String pass){

        //columns to fetch
        String[] columns = {
                SQLiteTable.USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        // validation
        String selection = SQLiteTable.EMAIL + " =? " + " AND " +
                SQLiteTable.PASSWORD + " =? ";

        //arguments
        String[] args = {email, pass};

        //query to user table
        Cursor cursor = db.query(SQLiteTable.TABLE,
                columns, //return
                selection, //where clause
                args, // value of the clause
                null,
                null,
                null);

        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0){
            Log.d(TAG, "return true");
            return true;
        }

        return false;
    }

    public boolean deleteUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(SQLiteTable.TABLE, SQLiteTable.EMAIL + " =? ",
                new String[]{email}) > 0;
    }
}
