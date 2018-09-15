package com.logistics.alucard.mvprojectapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logistics.alucard.mvprojectapp.presenter.ILoginPresenter;
import com.logistics.alucard.mvprojectapp.presenter.LoginPresenter;
import com.logistics.alucard.mvprojectapp.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {
    private static final String TAG = "MainActivity";

    private ILoginPresenter loginPresenter;
    private EditText etUser, etPass, etEmail;
    Button btSumbit, btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init view
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etEmail = findViewById(R.id.etEmail);
        btSumbit = findViewById(R.id.btSubmit);
        btRegister = findViewById(R.id.btRegister);

        //init
        loginPresenter = new LoginPresenter(this);

        //event
        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLogin(etUser.getText().toString(), etPass.getText().toString(), etEmail.getText().toString());
            }
        });
    }

    @Override
    public void onLoginSuccess(String message) {
        Log.d(TAG, "onLoginSuccess: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Log.d(TAG, "onLoginError: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
