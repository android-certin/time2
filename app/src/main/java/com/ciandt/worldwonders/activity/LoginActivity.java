package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.User;

/**
 * Created by andersonr on 20/08/15.
 */
public class LoginActivity extends AppCompatActivity {
    private final int REQUEST_SIGNUP = 1;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("LoginActivity", "onCreate");

        Button buttonSignup = (Button) findViewById(R.id.btn_signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("LoginActivity", "onActivityResult");

        if (data != null) {
            switch (requestCode) {
                case REQUEST_SIGNUP:
                    user = (User) data.getParcelableExtra("user");
                    if(user != null) {
                        TextView textUsername = (TextView) findViewById(R.id.edit_username);
                        textUsername.setText(user.getUsername());
                    }
                break;
            }
        }
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
