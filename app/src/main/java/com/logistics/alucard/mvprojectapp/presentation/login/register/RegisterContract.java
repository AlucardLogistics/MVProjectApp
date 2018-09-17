package com.logistics.alucard.mvprojectapp.presentation.login.register;

import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.bases.BasePresenter;
import com.logistics.alucard.mvprojectapp.bases.BaseView;

public interface RegisterContract {

    interface View extends BaseView<Presenter> {
        void showSuccessfulRegister(String message);
        void showErrorRegister(String message);
        void navigateTo();
    }

    interface Presenter extends BasePresenter {
        boolean validateRegisterFields(EditText[] fields);
    }
}
