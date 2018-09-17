package com.logistics.alucard.mvprojectapp.presentation.login.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.presentation.login.musicplayer.MusicFragment;
import com.logistics.alucard.mvprojectapp.R;
import com.logistics.alucard.mvprojectapp.bases.BaseActivity;
import com.logistics.alucard.mvprojectapp.presentation.login.register.RegisterActivity;

import static android.support.v4.util.Preconditions.checkNotNull;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";


    private EditText etUser, etPass, etEmail;
    Button btSumbit, btRegister;

    LoginContract.Presenter mPresenter;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init view
        //etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etEmail = findViewById(R.id.etEmail);
        btSumbit = findViewById(R.id.btSubmit);
        btRegister = findViewById(R.id.btRegister);

        //event
        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.validateLoginFields(new EditText[]{etEmail, etPass});
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void showSuccessfulMessage(String message) {
        showMessageToast(message);
        navigateTo();
    }

    @Override
    public void showFailedMessage(String message) {
        showMessageToast(message);
    }

    @Override
    public void navigateTo() {

        Bundle b = new Bundle();
        b.putString("emailID", etEmail.getText().toString());
        Log.d(TAG, "navigateTo: emailValue: " + etEmail.getText().toString());

        MusicFragment musicFrag = new MusicFragment();
        musicFrag.setArguments(b);

        getSupportFragmentManager().beginTransaction()
        .replace(R.id.mainActivity, musicFrag)
        .addToBackStack(null)
        .commit();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter = new LoginPresenter(this, this);
    }
}

//    @Override
//    public void onLoginSuccess(String message) {
//        Log.d(TAG, "onLoginSuccess: " + message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.mainActivity, new MusicFragment())
//                .addToBackStack(null)
//                .commit();
//    }
//
//    @Override
//    public void onLoginError(String message) {
//        Log.d(TAG, "onLoginError: " + message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
