package com.example.ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;
    private EditText et1,et2;
    private SharedPreferences prefe1;
    private Button btnCorte;
    private TextView tnmView;
    private DbHandler dbManager;
    private String result = "";
    private TextView item;
    private RelativeLayout rl;
    private TextView corte;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent b1 = new Intent(Main2Activity.this,Main4Activity.class);
                    startActivity(b1);
                    finish();
                    break;
                case R.id.navigation_dashboard:

                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        final DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(Main2Activity.this, userList, R.layout.list_row,new String[]{"name","location"}, new int[]{R.id.name, R.id.location});
        lv.setAdapter(adapter);


        item = (TextView)findViewById(R.id.action_corte);
        rl = (RelativeLayout)findViewById(R.id.rlInvisible);

        if (adapter.isEmpty()){
            rl.setVisibility(View.VISIBLE);
        }else {

        }

        dbManager = new DbHandler(this);
        Cursor Distance = dbManager.Distance();

        if (Distance.moveToNext())
            result = String.valueOf(Distance.getDouble(Distance.getColumnIndex("myTotal")));




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_corte, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_corte);

        View actionView = MenuItemCompat.getActionView(menuItem);
        corte = (TextView) actionView.findViewById(R.id.txtCorte);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }
    private void setupBadge() {

        corte.setText(String.valueOf(result));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case R.id.action_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);

                builder.setTitle("Titulo")
                        .setMessage("El Mensaje para el usuario")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dbManager.DeleteTable();
                                        Intent i = new Intent(Main2Activity.this,MainActivity.class);
                                        startActivity(i);
                                        Toast.makeText(getApplicationContext(),"Lista limpio",Toast.LENGTH_LONG).show();
                                        DbHandler dbHandler = new DbHandler(Main2Activity.this);
                                        dbHandler.insertCorteDetails(result);
                                        finish();
                                    }
                                })
                        .setNegativeButton("CANCELAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                builder.create();
                builder.show();
                return true;
            case R.id.action_corte:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
