package com.logistics.alucard.mvprojectapp.presentation.login.musicplayer;

import android.content.Context;
import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.R;
import com.logistics.alucard.mvprojectapp.data.database.sqlite.SQLiteController;
import com.logistics.alucard.mvprojectapp.data.model.User;

public class MusicPresenter implements MusicContract.Presenter {
    private static final String TAG = "MusicPresenter";

    MusicContract.View mView;
    Context mContext;
    SQLiteController sqLiteController;
    String username, pass, email;

    public MusicPresenter(MusicContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.sqLiteController = new SQLiteController(mContext);
        mView.setPresenter(this);
    }

    @Override
    public void getUserEmail(String email) {
        sqLiteController.getUserDetails(email);
        getUserDetail(sqLiteController.getUserDetails(email));
    }

    @Override
    public void logOut(String email) {
        if (email != null){
            mView.logOut();
        }
    }

    @Override
    public void getUserDetail(User user) {
        mView.loadUserDetail(user);
    }

    @Override
    public void deleteAccountPermanent(String email) {
        sqLiteController.deleteAccount(email);
        if (!sqLiteController.deleteAccount(email)){
            mView.showSuccess("Account Removed Successfully");
            mView.navigateToNextPage();
        }else{
            mView.showFailed("Something went Wrong!");
        }
    }

    @Override
    public boolean validateUpdateFields(EditText[] fields) {
        for (EditText fieldsCounter: fields) {
            if (fieldsCounter.getText().toString().isEmpty()){
                fieldsCounter.setError("Field Required!");
                return false;
            }
        }
        validated(fields);
        return true;
    }

    private void validated(EditText[] fields){

        username = fields[0].getText().toString();
        pass = fields[1].getText().toString();
        email = fields[2].getText().toString();

        if (updateUserData(username, email, pass) == 1){
            mView.showSuccess("Update data successfully!");
            mView.refreshPage(email);
        }else{
            mView.showFailed("Updated data Failed");
        }

    }

    private int updateUserData(String username, String email, String pass){
        User user = new User(username, email, pass);
        return sqLiteController.updateUserData(user, email);
    }

    @Override
    public void start() {

    }
}
