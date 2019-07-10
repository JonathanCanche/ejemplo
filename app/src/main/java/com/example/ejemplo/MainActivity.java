package com.example.ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
    private TextView ContadorTorta;
    private TextView ContadorTaco;
    private TextView ContadorBuger;
    private TextView total;
    private Button btnclean;
    private Button btnCambio;
    private Button btnOpen;
    private EditText txtdinero;
    private int mostrar;
    private int click = 0;
    private Date hora;
    private String Vtorta = " torta";
    private String Vtaco = " taco";
    private String Vburger = " Burger";
    private String n;
    private String s;
    private String p;

    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hora=new Date();

        MasTorta = (ImageView)findViewById(R.id.tortaMas);
        MenosTorta = (ImageView)findViewById(R.id.tortaMenos);
        MasTaco = (ImageView)findViewById(R.id.tacoMas);
        MenosTaco = (ImageView)findViewById(R.id.tacoMenos);
        MasBurger = (ImageView)findViewById(R.id.burgerMas);
        MenosBurger = (ImageView)findViewById(R.id.burgerMenos);
        ContadorTorta = (TextView)findViewById(R.id.ContadorTorta);
        ContadorTaco = (TextView)findViewById(R.id.ContadorTaco);
        ContadorBuger = (TextView)findViewById(R.id.ContadorBurger);
        btnclean = (Button)findViewById(R.id.btnLimpiar);
        btnCambio = (Button)findViewById(R.id.btnCalcular);
        btnOpen = (Button)findViewById(R.id.btnabrir);
        txtdinero = (EditText)findViewById(R.id.TxtDinero);
        total = (TextView)findViewById(R.id.TotalTodo);


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
                Toast.makeText(getApplicationContext(),"Acción no permitida"+p,Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(),"Acción no permitida"+p,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void limpiar(){

        ContadorBuger.setText("0");
        ContadorTorta.setText("0");
        ContadorTaco.setText("0");
        total.setText("0");
        txtdinero.setText("");
        mostrar = 0;
        n =null;
        s=null;

    }

    public void DarCambio(){
        if (mostrar > Integer.parseInt(txtdinero.getText().toString())){
            Toasty.error(getApplicationContext(), "Operación invalida,cantidad insuficiente", Toast.LENGTH_SHORT, true).show();
        }else {
            txtdinero.getText().toString();
            int dinero = Integer.valueOf(txtdinero.getText().toString());
            int resultado = dinero - mostrar;

            if (n==null || n.equals("0 torta")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {s};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else if (s==null || s.equals("0 taco")){
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+n, Toast.LENGTH_SHORT, true).show();
                String[]  Total_Score =  new String[] {n};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }else {
                Toasty.success(getApplicationContext(), "Su cambio: "+resultado+s+n, Toast.LENGTH_SHORT, true).show();

                String[]  Total_Score =  new String[] {n,s};

                String result_ScoreP1 = ("" + Arrays.asList(Total_Score)).
                        replaceAll("(^.|.$)", "  ").replace(", ", "  , " );

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(Integer.toString(mostrar),result_ScoreP1);
            }

        }
    }

}
