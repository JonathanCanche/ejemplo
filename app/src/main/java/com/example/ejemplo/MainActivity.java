package com.example.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText txtdinero;
    private int mostrar;
    private int click = 0;


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
        ContadorTorta = (TextView)findViewById(R.id.ContadorTorta);
        ContadorTaco = (TextView)findViewById(R.id.ContadorTaco);
        ContadorBuger = (TextView)findViewById(R.id.ContadorBurger);
        btnclean = (Button)findViewById(R.id.btnLimpiar);
        btnCambio = (Button)findViewById(R.id.btnCalcular);
        txtdinero = (EditText)findViewById(R.id.TxtDinero);
        total = (TextView)findViewById(R.id.TotalTodo);

        btnCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtdinero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Acción no permitida,introduzca una cantidad",Toast.LENGTH_LONG).show();
                }else {
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
    }

    public void DarCambio(){
        if (mostrar > Integer.parseInt(txtdinero.getText().toString())){
            Toast.makeText(getApplicationContext(),"Operación invalida,cantidad insuficiente",Toast.LENGTH_LONG).show();
        }else {
            txtdinero.getText().toString();
            int dinero = Integer.valueOf(txtdinero.getText().toString());
            int resultado = dinero - mostrar;
            Toast.makeText(getApplicationContext(),"Su cambio: "+resultado,Toast.LENGTH_LONG).show();
        }
    }
}
