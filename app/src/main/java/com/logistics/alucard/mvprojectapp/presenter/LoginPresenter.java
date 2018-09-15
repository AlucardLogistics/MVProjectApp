package com.logistics.alucard.mvprojectapp.presenter;

import android.util.Log;

import com.logistics.alucard.mvprojectapp.User.User;
import com.logistics.alucard.mvprojectapp.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
    private static final String TAG = "LoginPresenter";

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String pass, String email) {
        User user = new User(username, pass, email);
        int loginCode = user.isValidData();
        Log.d(TAG, "onLogin: *********passLength: " + user.getPass().length());
        Log.d(TAG, "onLogin: *********************loginCOde: " + loginCode);

        if(loginCode == 0 )
            loginView.onLoginError("User can't be Empty.");
        else if(loginCode == 1)
            loginView.onLoginError("Password must be minimum 6 characters long.");
        else if(loginCode == 2)
            loginView.onLoginError("Email can't be Empty.");
        else if(loginCode == 3)
            loginView.onLoginError("Not a valid Email.");
        else
            loginView.onLoginSuccess("Welcome");
    }
}
