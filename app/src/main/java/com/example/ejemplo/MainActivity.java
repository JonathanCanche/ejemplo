package com.example.ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ImageView MasTorta;
    private ImageView MenosTorta;
    private ImageView MasTaco;
    private ImageView MenosTaco;
    private ImageView MasBurger;
    private ImageView MenosBurger;
    private ImageView MasOrden;
    private ImageView MenosOrden;
    private TextView ContadorTorta;
    private TextView ContadorTaco;
    private TextView ContadorBuger;
    private TextView ContadorOrden;
    private Switch CambiarPrecio;
    private TextView total;
    private Button btnclean;
    private Button btnCambio;
    private Button btnOpen;
    private EditText txtdinero;
    private EditText NuevaCantidad;
    private int mostrar;
    private int click = 0;
    private Date hora;
    private String Vtorta = " torta";
    private String Vtaco = " taco";
    private String Vburger = " burger";
    private String VOrden = " orden";
    private String n;
    private String s;
    private String p;
    private String o;

    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;

    private TextView smsCountTxt;
    private int pendingSMSCount = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MasTorta = (ImageView)findViewById(R.id.tortaMas);
        MenosTorta = (ImageView)findViewById(R.id.tortaMenos);
        MasTaco = (ImageView)findViewById(R.id.tacoMas);
        MenosTaco = (ImageView)findViewById(R.id.tacoMenos);
        MasBurger = (ImageView)findViewById(R.id.burgerMas);
        MenosBurger = (ImageView)findViewById(R.id.burgerMenos);
        MasOrden = (ImageView)findViewById(R.id.OrdenMas);
        MenosOrden = (ImageView)findViewById(R.id.OrdenMenos);
        ContadorTorta = (TextView)findViewById(R.id.ContadorTorta);
        ContadorTaco = (TextView)findViewById(R.id.ContadorTaco);
        ContadorBuger = (TextView)findViewById(R.id.ContadorBurger);
        ContadorOrden = (TextView)findViewById(R.id.ContadorOrden);
        btnclean = (Button)findViewById(R.id.btnLimpiar);
        btnCambio = (Button)findViewById(R.id.btnCalcular);
        btnOpen = (Button)findViewById(R.id.btnabrir);
        txtdinero = (EditText)findViewById(R.id.TxtDinero);
//        total = (TextView)findViewById(R.id.TotalTodo);
        CambiarPrecio = (Switch)findViewById(R.id.switch1);
        NuevaCantidad = (EditText)findViewById(R.id.editText);


        CambiarPrecio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    NuevaCantidad.setEnabled(true);
                    ContadorOrden.setText("0");
                    limpiar();

                }else {
                    NuevaCantidad.setEnabled(false);
                    ContadorOrden.setText("0");
                    limpiar();
                }
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        btnCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContadorBuger.getText().toString().equals("0") && ContadorTorta.getText().toString().equals("0") && ContadorTaco.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"Seleccione algun producto",Toast.LENGTH_LONG).show();
                }else if(txtdinero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Introduzca una cantidad",Toast.LENGTH_LONG).show();
                }else{
                    DarCambio();
                }
            }
        });

        btnclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limpiar();
                Intent intent = new Intent(MainActivity.this,Main5Activity.class);
                startActivity(intent);
            }
        });


        MasOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CambiarPrecio.isChecked()==false){
                    mostrar = mostrar + 60;
                    click = Integer.parseInt(ContadorOrden.getText().toString());
                    click = click + 1;
                    ContadorOrden.setText(String.valueOf(click));
                    total.setText(String.valueOf(mostrar));
                    o = click + VOrden;
                }else{
                    if (NuevaCantidad.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Introduzca una cantidad",Toast.LENGTH_LONG).show();
                    }else {
                        mostrar = mostrar + Integer.parseInt(NuevaCantidad.getText().toString());
                        click = Integer.parseInt(ContadorOrden.getText().toString());
                        click = click + 1;
                        ContadorOrden.setText(String.valueOf(click));
                        total.setText(String.valueOf(mostrar));
                        o = click + VOrden;
                    }
                }
            }
        });

        MenosOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(ContadorOrden.getText().toString())<1){
                    Toast.makeText(getApplicationContext(),"Acción no permitida",Toast.LENGTH_LONG).show();
                }else {
                    if (CambiarPrecio.isChecked()==false){
                        mostrar = mostrar - 60;

                        click = Integer.parseInt(ContadorOrden.getText().toString());
                        click = click - 1;
                        ContadorOrden.setText(String.valueOf(click));
                        total.setText(String.valueOf(mostrar));
                        o = click + VOrden;
                    }else{
                        mostrar = mostrar - Integer.parseInt(NuevaCantidad.getText().toString());

                        click = Integer.parseInt(ContadorOrden.getText().toString());
                        click = click - 1;
                        ContadorOrden.setText(String.valueOf(click));
                        total.setText(String.valueOf(mostrar));
                        o = click + VOrden;
                    }
                }
            }
        });


        MasTorta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = Integer.parseInt(ContadorTorta.getText().toString());
                mostrar = mostrar + 18;
                click = click + 1;
                ContadorTorta.setText(String.valueOf(click));
                total.setText(String.valueOf(mostrar));
                n = click + Vtorta;
            }
        });

        MenosTorta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(ContadorTorta.getText().toString())<1){
                    Toast.makeText(getApplicationContext(),"Acción no permitida",Toast.LENGTH_LONG).show();
                }else {
                    click = Integer.parseInt(ContadorTorta.getText().toString());
                    mostrar = mostrar - 18;
                    click = click - 1;
                    ContadorTorta.setText(String.valueOf(click));
                    total.setText(String.valueOf(mostrar));
                    n = click + Vtorta;
                }
            }
        });

        MasTaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = Integer.parseInt(ContadorTaco.getText().toString());
                mostrar = mostrar + 10;
                click = click + 1;
                ContadorTaco.setText(String.valueOf(click));
                total.setText(String.valueOf(mostrar));
                s = click + Vtaco;
            }
        });

        MenosTaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(ContadorTaco.getText().toString())<1){
                    Toast.makeText(getApplicationContext(),"Acción no permitida",Toast.LENGTH_LONG).show();
                }else {
                    click = Integer.parseInt(ContadorTaco.getText().toString());
                    mostrar = mostrar - 10;
                    click = click - 1;
                    ContadorTaco.setText(String.valueOf(click));
                    total.setText(String.valueOf(mostrar));
                    s = click + Vtaco;
                }
            }
        });

        MasBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = Integer.parseInt(ContadorBuger.getText().toString());
                mostrar = mostrar + 25;
                click = click + 1;
                ContadorBuger.setText(String.valueOf(click));
                total.setText(String.valueOf(mostrar));
                p = click + Vburger;
            }
        });

        MenosBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(ContadorBuger.getText().toString())<1){
                    Toast.makeText(getApplicationContext(),"Acción no permitida",Toast.LENGTH_LONG).show();
                }else {
                    click = Integer.parseInt(ContadorBuger.getText().toString());
                    mostrar = mostrar - 25;
                    click = click - 1;
                    ContadorBuger.setText(String.valueOf(click));
                    total.setText(String.valueOf(mostrar));
                    p = click + Vburger;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_notifications);

        View actionView = MenuItemCompat.getActionView(menuItem);
        total = (TextView) actionView.findViewById(R.id.notification_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_notifications: {

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        total.setText(String.valueOf(mostrar));
    }

    public void limpiar(){

        ContadorBuger.setText("0");
        ContadorTorta.setText("0");
        ContadorTaco.setText("0");
        ContadorOrden.setText("0");
        NuevaCantidad.setText("");
        total.setText("0");
        txtdinero.setText("");
        mostrar = 0;
        n =null;
        s=null;
        p=null;

    }

    public void DarCambio(){
        if (mostrar > Integer.parseInt(txtdinero.getText().toString())){
            Toasty.error(getApplicationContext(), "Cantidad insuficiente", Toast.LENGTH_SHORT, true).show();
        }else {
            txtdinero.getText().toString();
            int dinero = Integer.valueOf(txtdinero.getText().toString());
            int resultado = dinero - mostrar;

            if ((s==null || s.equals("0 taco")) && (p==null || p.equals("0 burger")) && (o==null || o.equals("0 orden"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+n, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);

            }else if ((p==null || p.equals("0 burger")) && (n==null || n.equals("0 torta")) && (o==null || o.equals("0 orden"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {s};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if ((s==null || s.equals("0 taco")) && (n==null || n.equals("0 torta")) && (o==null || o.equals("0 orden"))){

                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+p, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {p};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if ((s==null || s.equals("0 taco")) && (n==null || n.equals("0 torta")) && (p==null || p.equals("0 burger"))){

                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (s==null || s.equals("0 taco") && (n==null || n.equals("0 torta"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+p+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {p,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (s==null || s.equals("0 taco") && (p==null || p.equals("0 burger"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+n+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (s==null || s.equals("0 taco") && (o==null || p.equals("0 orden"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+n+p, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,p};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (n==null || n.equals("0 torta") && (p==null || p.equals("0 burger"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {s,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (n==null || n.equals("0 torta") && (o==null || o.equals("0 orden"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+p, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {s,p};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (p==null || p.equals("0 burger") && (o==null || o.equals("0 orden"))){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+n, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,s};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (s==null || s.equals("0 taco")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+n+p+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,p,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (n==null || n.equals("0 torta")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+p+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {s,p,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (p==null || p.equals("0 burger")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+n+o, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,s,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (o==null || o.equals("0 orden")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+n+p, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n,s,p};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else {
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+n, Toast.LENGTH_SHORT, true).show();

                String[]  Total_Score =  new String[] {n,s,p,o};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }

        }
    }

}
