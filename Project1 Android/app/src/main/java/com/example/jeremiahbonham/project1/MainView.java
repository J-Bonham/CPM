// Jeremiah Bonham
// CPM 1503

package com.example.jeremiahbonham.project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainView extends Activity{

    ListView list;
    Button addNew;
    Button logout;
    ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, "nbK5KB7EQLKUCc8GXTCH0ihL8T7mJ5Cu3grvhx3o", "1X9JXEFnW6JAxjPWSXM9j4sTRASXpatLcYrpodEf");
        list = (ListView) findViewById(R.id.list);
        addNew = (Button) findViewById(R.id.addItem);
        logout = (Button) findViewById(R.id.logout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    public void addNewItemClick(View view) {
        Intent addItemIntent = new Intent(MainView.this, AddItemActivity.class);
        startActivityForResult(addItemIntent, 0);
    }

    public void onLogoutClick(View view) {

        Intent logout = new Intent(MainView.this, SignInActivity.class);
        startActivityForResult(logout, 0);
    }

    public void checkUserStatus() {
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser == null) {
            Intent loginIntent = new Intent(this, SignInActivity.class);
            startActivity(loginIntent);
            finish();

        } else {

            getDataList();
        }
    }

    public void getDataList() {

        dataList = new ArrayList<>();

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Items");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> networkItems, ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : networkItems) {
                        String objectID = parseObject.getObjectId();
                        String itemname = parseObject.getString("itemname");
                        String ip = parseObject.getString("ip");
                        Boolean wirelessCheck = parseObject.getBoolean("wireless");

                        HashMap<String, String> data = new HashMap<>();
                        data.put("objectId", objectID);
                        data.put("itemname", itemname);
                        data.put("ip", ip);

                        if (wirelessCheck) {
                            data.put("wireless", "Wireless");
                        } else {
                            data.put("wireless", "Not Wireless");
                        }
                        dataList.add(data);
                    }

                    String[] mapFrom = {"itemname", "ip", "wireless"};
                    int[] mapTo = {R.id.itemNameCustomRow, R.id.ipaddressCustomRow, R.id.wirelessCustomRow};

                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), dataList, R.layout.customrow, mapFrom, mapTo);
                    list.setAdapter(adapter);
                    list.setOnItemLongClickListener(deleteListItem());
                    list.setOnItemClickListener(updateList());

                }
            }
        });
    }


    protected AdapterView.OnItemLongClickListener deleteListItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                    final HashMap<String, String> parseObjectData = dataList.get(position);

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainView.this);
                    alert.setTitle("Delete Item?");
                    alert.setMessage("Permanently delete?");
                    alert.setNegativeButton("No", null);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Items");
                            parseQuery.getInBackground(parseObjectData.get("objectId"), new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject parseObject, ParseException e) {
                                    if (e == null) {
                                        parseObject.deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                getDataList();
                                            }
                                        });

                                    }

                                }

                            });

                        }

                    });

                alert.show();

                return true;

            }

        };
    }


    protected AdapterView.OnItemClickListener updateList() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    HashMap<String, String> parseObjectHash = dataList.get(position);
                    Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                    intent.putExtra("objectId", parseObjectHash.get("objectId"));
                    startActivityForResult(intent, 0);
                }

        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}





