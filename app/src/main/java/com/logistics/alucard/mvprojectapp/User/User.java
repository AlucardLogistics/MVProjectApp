package com.logistics.alucard.mvprojectapp.User;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

public class User implements IUser {
    private static final String TAG = "User";

    String username, password, email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
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
