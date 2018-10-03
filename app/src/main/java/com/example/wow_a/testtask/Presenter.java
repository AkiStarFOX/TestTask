package com.example.wow_a.testtask;

import android.util.Log;

public class Presenter {
    private ViewInterface view;
    private Model model;
    private String login;
    private String password;

    public Presenter(ViewInterface view) {
        this.view = view;
        model = new Model();
    }

    public void checkValid() {
        login = view.getEmail();
        password = view.getPassword();
        if (model.validate(login, password)) {
            view.isLoginValid();
        } else {
            view.showValidError();
        }
    }
}
