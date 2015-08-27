package com.ciandt.worldwonders.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.fragment.LoginFragment;
import com.ciandt.worldwonders.fragment.WonderDetailFragment;
import com.ciandt.worldwonders.fragment.WondersFragment;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.User;

/**
 * Created by andersonr on 20/08/15.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    LoginFragment loginFragment;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        fragmentManager = getSupportFragmentManager();

        loginFragment = new LoginFragment();
        loginFragment.setOnLoginListener(new LoginFragment.OnLoginListener() {
            @Override
            public void onLogin(User user) {
                inflateFragment();
            }
        });

            replaceFragment(loginFragment, R.id.fragment_container);
    }



    private void inflateFragment(){
        replaceFragment(new WondersFragment(), R.id.fragment_container);
    }

    private void replaceFragment(Fragment fragment, int idDrawable) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(idDrawable, fragment)
                .commit();


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
