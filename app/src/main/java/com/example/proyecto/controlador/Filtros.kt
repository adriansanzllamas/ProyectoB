package com.example.proyecto.controlador

import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.os.Bundle
import com.example.proyecto.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.controlador.Filtros

class Filtros : AppCompatActivity() {
    private var bar: SeekBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtros)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        bar = findViewById(R.id.barra)


        //obtener el intent de la anterior clase o pantalla
        val intent = intent
    }

    //mostrar el menu con la toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion1 -> {
                val intent = Intent(this, Filtros::class.java)
                startActivity(intent)
                return true
            }
        }
        return true
    }
}