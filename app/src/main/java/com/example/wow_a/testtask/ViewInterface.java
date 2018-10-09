package com.example.wow_a.testtask;

public interface ViewInterface {
    String getEmail();
    String getPassword();
    void isLoginValid();
    void showValidError();
    void showProgressBar();
    void hideProgressBar();
}
