package com.logistics.alucard.mvprojectapp.presentation.login.register;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.R;
import com.logistics.alucard.mvprojectapp.data.database.sqlite.SQLiteController;
import com.logistics.alucard.mvprojectapp.data.model.User;

public class RegisterPresenter implements RegisterContract.Presenter {

    @NonNull
    RegisterContract.View mView;
    String username, pass, email;
    Context context;
    SQLiteController sqliteController;

    public RegisterPresenter(@NonNull RegisterContract.View view, Context mContext){
        mView = view;
        context = mContext;
        mView.setPresenter(this);
        sqliteController = new SQLiteController(context);
    }



    @Override
    public boolean validateRegisterFields(EditText[] fields) {
        for (EditText field: fields){
            if (field.getText().toString().isEmpty()){
                field.setError("Field Required!");
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


        if (savingData(username, email, pass)){
            mView.showSuccessfulRegister("Register Successfully");
        }else{
            mView.showErrorRegister("Something went wrong!");
        }

    }

    private boolean savingData(String username, String email, String pass){
        User user = new User(username, email, pass);
        return sqliteController.saveUserData(user);
    }

    @Override
    public void start() {

    }
}
