package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView texto;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.toolbar);
        texto=findViewById(R.id.textView);
        setSupportActionBar(toolbar);


        //retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viewnextandroid.mocklab.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //necesitamos una intancia para la interfaz

        llamada llamada= retrofit.create(com.example.proyecto.llamada.class);

        Call<Facturas> call = llamada.getData();
        call.enqueue(new Callback<Facturas>() {
            @Override
            public void onResponse(Call<Facturas> call, Response<Facturas> response) {
                //comprobar la respuesta
                if(response.code() !=200){
                    texto.setText("comprueba la conexion");
                }
                String datos="";

                    datos = "descEstado:" + response.body().getDescEstado() +
                            "\n importeOrdenacion: " + response.body().getImporteOrdenacion() +
                            " \n fecha: " + response.body().getFecha();
                    texto.append(datos);

            }

            @Override
            public void onFailure(Call<Facturas> call, Throwable t) {

            }
        });

//esto es un comentario



    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        return false;

    }


}