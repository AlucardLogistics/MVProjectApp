package com.logistics.alucard.mvprojectapp.presentation.login.musicplayer;

import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.bases.BasePresenter;
import com.logistics.alucard.mvprojectapp.bases.BaseView;
import com.logistics.alucard.mvprojectapp.data.model.User;

public interface MusicContract {

    interface View extends BaseView<Presenter> {
        void loadUserDetail(User user);
        void showSuccess(String message);
        void showFailed(String message);
        void navigateToNextPage();
        void refreshPage(String email);
        void logOut();
    }

    interface Presenter extends BasePresenter {
        void getUserEmail(String email);
        void logOut(String email);
        void getUserDetail(User user);
        void deleteAccountPermanent(String email);
        boolean validateUpdateFields(EditText[] fields);
    }
}
