// Jeremiah Bonham
// CPM 1503

package com.example.jeremiahbonham.project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignInActivity extends Activity {

    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        name = (EditText) findViewById(R.id.userNameInput);
        password = (EditText) findViewById(R.id.passwordInput);

    }

    public void onSignUpClick(View view) {
        Intent registerAccountIntent = new Intent(this, SignUpActivity.class);
        startActivityForResult(registerAccountIntent, 0);
    }

    public void onSignInClick(View view) {

        if ((name.getText().toString().isEmpty() || password.getText().toString().isEmpty())) {
            AlertDialog.Builder error = new AlertDialog.Builder(SignInActivity.this);
            error.setTitle("Problem Signing in.");
            error.setMessage("Please check your username and password");
            error.setNegativeButton("OK", null);
            error.setCancelable(false);
            error.show();

        } else {

            String userName = name.getText().toString();
            String userPassword = password.getText().toString();
            ParseUser.logInInBackground(userName, userPassword, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null) {
                        Intent goToMain = new Intent(SignInActivity.this, MainView.class);
                        startActivity(goToMain);
                    }

                }
            });
        }
    }


}


