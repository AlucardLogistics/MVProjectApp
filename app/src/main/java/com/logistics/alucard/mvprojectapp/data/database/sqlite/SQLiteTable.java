package com.logistics.alucard.mvprojectapp.data.database.sqlite;

public class SQLiteTable {

    //public static final String MYDATABASE = "myDataBase";
    public static final String TABLE = "userTable";
    public static final String USER_ID = "col_id";
    public static final String USERNAME = "col_user";
    public static final String PASSWORD = "col_pass";
    public static final String EMAIL = "col_email";
    public static final int VERSION = 1;

    public static final String DB_USER = "CREATE TABLE " + TABLE + "("
            + USER_ID + " INTEGER PRIMARY KEY," + USERNAME + " TEXT," + PASSWORD + " TEXT,"
            + EMAIL + " TEXT" + ")";
}
