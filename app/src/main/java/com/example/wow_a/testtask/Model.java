package com.example.wow_a.testtask;

import javax.security.auth.callback.Callback;

public class Model {


    private boolean isValid;


    public boolean validate(String login, String password) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (login.equals("test@mail.ru") && password.equals("1234567")) {
            return true;
        } else
            return false;
    }
}
