package com.ciandt.worldwonders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by andersonr on 20/08/15.
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("LoginActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LoginActivity", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LoginActivity", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LoginActivity", "onDestroy");
    }
}
