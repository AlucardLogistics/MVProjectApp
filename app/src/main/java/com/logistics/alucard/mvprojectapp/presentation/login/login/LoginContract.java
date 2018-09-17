package com.logistics.alucard.mvprojectapp.presentation.login.login;

import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.bases.BasePresenter;
import com.logistics.alucard.mvprojectapp.bases.BaseView;

public class LoginContract {

    interface View extends BaseView<Presenter> {
        void showSuccessfulMessage(String message);
        void showFailedMessage(String message);
        void navigateTo();
    }

    interface Presenter extends BasePresenter {
        boolean validateLoginFields(EditText[] fields);
    }
}
