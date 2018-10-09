package com.example.wow_a.testtask;

import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Presenter {
    private ViewInterface view;
    private Model model;


    public Presenter(ViewInterface view) {
        this.view = view;
        model = new Model();
    }

    public void checkValid()  {
        view.showProgressBar();
        String login = view.getEmail();
        String password = view.getPassword();

            model.validate(login, password, new Model.OnValidCheckedListener() {
                @Override
                public void onChecked(boolean isValid) {
                    if (!isValid){
                        view.hideProgressBar();
                        view.showValidError();
                    }else {
                        view.isLoginValid();
                    }
                }
            });





    }
}
