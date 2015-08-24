package com.ciandt.worldwonders.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.activity.SignupActivity;
import com.ciandt.worldwonders.model.User;

/**
 * Created by andersonr on 21/08/15.
 */
public class LoginFragment extends Fragment {
    private final int REQUEST_SIGNUP = 1;

    private User user;

    Button buttonSignup;
    Button buttonLogin;
    TextView textUsername;
    TextView textPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonSignup = (Button) view.findViewById(R.id.btn_signup);
        buttonLogin = (Button) view.findViewById(R.id.btn_login);
        textUsername = (TextView) view.findViewById(R.id.edit_username);
        textPassword = (TextView) view.findViewById(R.id.edit_password);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onLoginListener.onLogin(new User());
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_SIGNUP:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    user = data.getParcelableExtra("user");
                    if(user != null) {
                        textUsername.setText(user.getUsername());
                    }
                }
                break;
        }
    }

    OnLoginListener onLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    public interface OnLoginListener {
        public void onLogin(User user);
    }
}
