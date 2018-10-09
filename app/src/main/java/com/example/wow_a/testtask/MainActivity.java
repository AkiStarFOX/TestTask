package com.example.wow_a.testtask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements ViewInterface {
    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private boolean isValidEmail, isValidPassword, isShowPass;
    private ImageView btnShowPass;
    public Presenter presenter;
    private ProgressBar progressBar;

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
        txtEmail.addTextChangedListener(textWatcher);
        txtPassword.addTextChangedListener(textWatcher);
        isValidEmail = false;
        isValidPassword = false;
        btnLogin.setOnClickListener(clickListener);

        btnShowPass = findViewById(R.id.btnShowPass);
        btnShowPass.setOnClickListener(clickListener);
        isShowPass = false;
        progressBar = findViewById(R.id.pbLogin);
        progressBar.setVisibility(View.INVISIBLE);


        presenter = new Presenter(this);


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
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
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (txtEmail.getText().hashCode() == s.hashCode()) {
                isValidEmail = s.toString().trim().matches(emailReg) && s.length() > 6;

            } else if (txtPassword.getText().hashCode() == s.hashCode()) {
                isValidPassword = s.toString().trim().matches(passwordReg) && s.length() > 5;

            }
            btnLogin.setEnabled(isValidEmail && isValidPassword);

        }
    };


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
        finish();
    }

    @Override
    public void showValidError() {
        Toast.makeText(getApplicationContext(), R.string.AuthError, Toast.LENGTH_SHORT).show();

    }

    public void showPass() {
        int selectionEnd = txtPassword.getSelectionEnd();
        int selectionStart = txtPassword.getSelectionStart();
        if (isShowPass) {
            txtPassword.setTransformationMethod(null);
            txtPassword.setSelection(selectionStart,selectionEnd);

        } else {
            txtPassword.setTransformationMethod(new PasswordTransformationMethod());
            txtPassword.setSelection(selectionStart,selectionEnd);

        }
    }

    public void showProgressBar() {
        txtEmail.setEnabled(false);
        txtPassword.setEnabled(false);
        btnLogin.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar() {
        txtEmail.setEnabled(true);
        txtPassword.setEnabled(true);
        btnLogin.setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


}

