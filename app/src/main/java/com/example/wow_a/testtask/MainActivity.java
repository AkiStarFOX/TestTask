package com.example.wow_a.testtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ViewInterface, TextWatcher, View.OnClickListener {
    private Button btnLogin;
    private TextView txtEmail, txtPassword;
    private boolean isValidEmail, isValidPassword, isShowPass;
    private ImageView btnShowPass;
    public Presenter presenter;

    String emailReg = "([a-zA-Z0-9_.-]+)@([a-z0-9_-]+)\\.([a-z]{2,6})";
    String passwordReg = "[a-zA-Z0-9]{6,40}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setEnabled(false);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail.addTextChangedListener(this);
        txtPassword.addTextChangedListener(this);
        isValidEmail = false;
        isValidPassword = false;
        btnLogin.setOnClickListener(this);
        presenter = new Presenter(this);
        btnShowPass = findViewById(R.id.btnShowPass);
        btnShowPass.setOnClickListener(this);
        isShowPass = false;


    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


        if (txtEmail.getText().hashCode() == editable.hashCode()) {
            if (!editable.toString().trim().matches(emailReg)) {

                txtEmail.setError("Неверная форма записи e-mail");
                isValidEmail = false;
            } else {
                isValidEmail = true;
            }


        } else if (txtPassword.getText().hashCode() == editable.hashCode()) {
            if (!editable.toString().trim().matches(passwordReg)) {
                txtPassword.setError("Неверная форма записи password");
                isValidPassword = false;
            } else {
                isValidPassword = true;
            }

        }

        loginEnable(isValidEmail, isValidPassword);
    }

    public void loginEnable(boolean mail, boolean password) {
        if (mail && password) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                presenter.checkValid();
                break;
            case R.id.btnShowPass:
                isShowPass = !isShowPass;
                showPass();
                break;
        }
    }

    public String getEmail() {
        return txtEmail.getText().toString().trim();
    }

    public String getPassword() {
        return txtPassword.getText().toString().trim();
    }

    @Override
    public void isLoginValid() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void showValidError() {
        Toast.makeText(getApplicationContext(), "Ошибка авторизации, проверьте правильность ввода", Toast.LENGTH_SHORT).show();

    }

    public void showPass() {
        if (isShowPass) {

            txtPassword.setTransformationMethod(null);

        } else {
            txtPassword.setTransformationMethod(new PasswordTransformationMethod());

        }
    }

}
