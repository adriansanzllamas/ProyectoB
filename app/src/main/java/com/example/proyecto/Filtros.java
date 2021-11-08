package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Filtros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //obtener el intent de la anterior clase o pantalla
        Intent intent=getIntent();

    }



    //mostrar el menu con la toolbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.opcion1:
                Intent intent = new Intent(this,Filtros.class);
                startActivity(intent);
                return true;

        }

        return true;

    }
}