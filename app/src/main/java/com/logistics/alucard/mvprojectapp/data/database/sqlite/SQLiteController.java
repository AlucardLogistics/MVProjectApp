package com.logistics.alucard.mvprojectapp.data.database.sqlite;

import android.content.ContentValues;
import android.content.Context;

import com.logistics.alucard.mvprojectapp.data.model.User;

public class SQLiteController {

   private SQLiteDbHelper sqlitedbHelper;
   Context context;

    public SQLiteController(Context context) {
        this.context = context;
        sqlitedbHelper = new SQLiteDbHelper(context);
    }

    public boolean saveUserData(User user){
        ContentValues values = new ContentValues();
        values.put(SQLiteTable.USERNAME, user.getUser());
        values.put(SQLiteTable.PASSWORD, user.getPass());
        values.put(SQLiteTable.EMAIL, user.getEmail());
        return sqlitedbHelper.insertData(SQLiteTable.TABLE, values);
    }

    public int updateUserData(User user, String email){
        ContentValues values = new ContentValues();
        values.put(SQLiteTable.USERNAME, user.getUser());
        values.put(SQLiteTable.PASSWORD, user.getPass());
        values.put(SQLiteTable.EMAIL, user.getEmail());
        return sqlitedbHelper.updateData(SQLiteTable.TABLE, values, email);
    }

    public boolean checkUserCredentials(String password, String email){
        return sqlitedbHelper.checkUser(password, email);
    }

    public User getUserDetails(String email){
        return sqlitedbHelper.getUserDetail(email);
    }

    public boolean deleteAccount(String email){
        return sqlitedbHelper.deleteUser(email);
    }
}
