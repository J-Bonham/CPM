// Jeremiah Bonham
// CPM 1503

package com.example.jeremiahbonham.project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddItemActivity extends Activity {

    EditText itemName;
    EditText ipAddress;
    CheckBox wireless;
    String updateParseObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);

        itemName = (EditText) findViewById(R.id.itemNameEntered);
        ipAddress = (EditText) findViewById(R.id.ipAddressEntered);
        wireless = (CheckBox) findViewById(R.id.wireless);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            updateParseObjectId = bundle.getString("objectId");

            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Items");
            parseQuery.getInBackground(updateParseObjectId, new GetCallback<ParseObject>() {

                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    if (e == null) {

                        String itemNameValue = parseObject.getString("itemname");
                        String ipAddressValue = parseObject.getString("ip");
                        Boolean wirelessValue = parseObject.getBoolean("wireless");

                        itemName.setText(itemNameValue);
                        ipAddress.setText(ipAddressValue);
                        wireless.setChecked(wirelessValue);
                    }
                }
            });

        }
    }

    public void onSaveClick(View view) {
        if ((itemName.getText().toString().isEmpty())) {
            AlertDialog.Builder error = new AlertDialog.Builder(AddItemActivity.this);
            error.setTitle("Cannot Add Item");
            error.setMessage("Please Enter an Item Name");
            error.setNegativeButton("OK", null);
            error.setCancelable(false);
            error.show();

        } else if (updateParseObjectId == null) {

            ParseObject parseObject = new ParseObject("Items");
            parseObject.put("itemname", itemName.getText().toString());
            parseObject.put("ip", ipAddress.getText().toString());
            parseObject.put("wireless", wireless.isChecked());
            parseObject.setACL(new ParseACL(ParseUser.getCurrentUser()));
            parseObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    finish();
                }
            });
        }

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Items");

        parseQuery.getInBackground(updateParseObjectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {

                    parseObject.put("itemname", itemName.getText().toString());
                    parseObject.put("ip", ipAddress.getText().toString());
                    parseObject.put("wireless", wireless.isChecked());

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            finish();
                        }
                    });
                }
            }
        });

    }


    public void onCancel2Click(View view) {
        finish();
    }
}