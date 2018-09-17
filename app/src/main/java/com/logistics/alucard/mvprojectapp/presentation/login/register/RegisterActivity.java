package com.logistics.alucard.mvprojectapp.presentation.login.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logistics.alucard.mvprojectapp.R;
import com.logistics.alucard.mvprojectapp.bases.BaseActivity;
import com.logistics.alucard.mvprojectapp.presentation.login.login.LoginActivity;

import static android.support.v4.util.Preconditions.checkNotNull;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    private static final String TAG = "RegisterActivity";

    private EditText etUser, etPass, etEmail;
    private Button btRegister;
    RegisterContract.Presenter mPresenter;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etUser = findViewById(R.id.etUserReg);
        etPass = findViewById(R.id.etPassReg);
        etEmail = findViewById(R.id.etEmailReg);
        btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                mPresenter.validateRegisterFields(new EditText[] {etUser, etPass, etEmail});
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showSuccessfulRegister(String message) {
        showMessageToast(message);
        navigateTo();
    }

    @Override
    public void showErrorRegister(String message) {
        showMessageToast(message);
    }

    @Override
    public void navigateTo() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerPresenter = new RegisterPresenter(this, this);
        mPresenter.start();
    }
}
