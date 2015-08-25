package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.User;

public class SignupActivity extends BaseActivity {

    private User user;

    private TextView textName;
    private TextView textUsername;
    private TextView textPassword;
    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textName = (TextView) findViewById(R.id.edit_name);
        textUsername = (TextView) findViewById(R.id.edit_username);
        textPassword = (TextView) findViewById(R.id.edit_password);
        buttonSignup = (Button) findViewById(R.id.btn_signup);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = new User(
                    textName.getText().toString(),
                    textUsername.getText().toString(),
                    textPassword.getText().toString()
                );


                Intent intent = new Intent();
                intent.putExtra("user", user);
                SignupActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
