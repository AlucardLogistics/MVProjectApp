package com.logistics.alucard.mvprojectapp.presentation.login.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.data.database.sqlite.SQLiteController;
import com.logistics.alucard.mvprojectapp.presentation.login.login.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    @NonNull
    LoginContract.View mView;
    Context mContext;
    String email, pass;
    SQLiteController sqLiteController;

    public LoginPresenter(@NonNull LoginContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.email = email;
        this.pass = pass;
        this.sqLiteController = new SQLiteController(mContext);
    }

    @Override
    public boolean validateLoginFields(EditText[] fields) {
        for (EditText field: fields){
            if (field.getText().toString().isEmpty()){
                field.setError("Provide the required field!");
                return false;
            }
        }
        validated(fields);
        return true;
    }

    private void validated(EditText[] fields){
        email = fields[0].getText().toString();
        pass = fields[1].getText().toString();
        checkCredentials(pass, email);
        Log.d(TAG, "validated: email: " + email + " pass: " + pass);

    }

    private void checkCredentials(String pass, String email){
        Log.d(TAG, "checkCredentials: email: " + email + " pass: " + pass );
        if (sqLiteController.checkUserCredentials(pass, email)){
            mView.showSuccessfulMessage("Login Successful");
            //mView.navigateTo();
        }else{
            mView.showFailedMessage("Wrong Email or Password");
        }
    }

    @Override
    public void start() {

    }

}

//    ILoginView loginView;
//    SQLiteController sqLiteController;
//
//    public LoginPresenter(ILoginView loginView) {
//        this.loginView = loginView;
//    }
//
//    @Override
//    public void onLogin(String username, String pass, String email) {
//        User user = new User(username, pass, email);
//        int loginCode = user.isValidData();
//        Log.d(TAG, "onLogin: *********passLength: " + user.getPass().length());
//        Log.d(TAG, "onLogin: *********************loginCOde: " + loginCode);
//
//        if(sqLiteController.checkUserCredentials(email, pass)) {
//            loginView.onLoginSuccess("Welcome");
//        } else {
//            if(loginCode == 0 )
//                loginView.onLoginError("User can't be Empty.");
//            else if(loginCode == 1)
//                loginView.onLoginError("Password must be minimum 6 characters long.");
//            else if(loginCode == 2)
//                loginView.onLoginError("Email can't be Empty.");
//            else if(loginCode == 3)
//                loginView.onLoginError("Not a valid Email.");
//        }
//
////        if(loginCode == 0 )
////            loginView.onLoginError("User can't be Empty.");
////        else if(loginCode == 1)
////            loginView.onLoginError("Password must be minimum 6 characters long.");
////        else if(loginCode == 2)
////            loginView.onLoginError("Email can't be Empty.");
////        else if(loginCode == 3)
////            loginView.onLoginError("Not a valid Email.");
////        else
////            loginView.onLoginSuccess("Welcome");
//    }
