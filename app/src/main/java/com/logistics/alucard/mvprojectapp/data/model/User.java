package com.logistics.alucard.mvprojectapp.data.model;

import android.text.TextUtils;
import android.util.Patterns;

public class User implements IUser {
    private static final String TAG = "User";

    int user_id;
    String username, password, email;


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int user_id, String username, String password, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUser() {
        return username;
    }

    @Override
    public String getPass() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int isValidData() {

        if (TextUtils.isEmpty(getUser()))
            return 0;
        else if (getPass().length() < 6)
            return 1;
        else if (TextUtils.isEmpty(getEmail()))
            return 2;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return 3;
        else
            return -1;

    }

}
