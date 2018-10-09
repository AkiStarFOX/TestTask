package com.example.wow_a.testtask;

import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Model {
    public interface OnValidCheckedListener {
        void onChecked(boolean isValid);
    }

    private OnValidCheckedListener onItemCheckedListener;



    public void validate(final String login, final String password, final OnValidCheckedListener listener){
        boolean isValid=false;

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                boolean a = true;
                listener.onChecked(login.equals("test@mail.ru") && password.equals("123456"));

            }
        };
        asyncTask.execute();

    }
}
