// Jeremiah Bonham
// CPM 1503

package com.example.jeremiahbonham.project1;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity{

    EditText newUserName;
    EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        newUserName = (EditText) findViewById(R.id.newUserNameInput);
        newPassword = (EditText) findViewById(R.id.newPasswordInput);
    }

    public void onProceedClick(View view) {
        if (newUserName.getText().toString().isEmpty() || newPassword.getText().toString().isEmpty()) {
            AlertDialog.Builder error = new AlertDialog.Builder(SignUpActivity.this);
            error.setTitle("Problem Creating Account");
            error.setMessage("Please enter a User Name and Password");
            error.setNegativeButton("OK", null);
            error.setCancelable(false);
            error.show();

        } else {

            ParseUser parseUser = new ParseUser();
            parseUser.setUsername(newUserName.getText().toString());
            parseUser.setPassword(newPassword.getText().toString());

            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        setResult(RESULT_OK);
                        finish();

                    } else {

                        AlertDialog.Builder error = new AlertDialog.Builder(SignUpActivity.this);
                        error.setTitle("Problem Creating Account");
                        error.setMessage(e.toString());
                        error.setNegativeButton("OK", null);
                        error.setCancelable(false);
                        error.show();

                    }
                }
            });
        }
    }

    public void onCancelClick(View view) {
        finish();
    }

}
