package com.example.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;
    private EditText et1,et2;
    private SharedPreferences prefe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(Main2Activity.this, userList, R.layout.list_row,new String[]{"name","location"}, new int[]{R.id.name, R.id.location});
        lv.setAdapter(adapter);

    }
}
