package com.example.ejemplo;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Main5Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> corteList = db.GetCorte();
        ListView lv = (ListView) findViewById(R.id.listCorte);
        ListAdapter adapter = new SimpleAdapter(Main5Activity.this, corteList, R.layout.list_row_dos,new String[]{"corte"}, new int[]{R.id.corte});
        lv.setAdapter(adapter);

    }

}
