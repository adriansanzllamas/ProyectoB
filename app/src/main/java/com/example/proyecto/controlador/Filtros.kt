package com.example.proyecto.controlador

import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.os.Bundle
import com.example.proyecto.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.controlador.Filtros

class Filtros : AppCompatActivity() {
    private var bar: SeekBar? = null
    private var diamesaño1: Button? =null
    private var desde: TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtros)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        bar = findViewById(R.id.barra)
        diamesaño1=findViewById(R.id.diamesaño1)
        desde=findViewById(R.id.desde)

        diamesaño1?.setOnClickListener {
            showDatePickerDialog()
        }



        //obtener el intent de la anterior clase o pantalla
        //val intent = intent
    }
    public fun showDatePickerDialog() {
        val datePicker=DatePickerFragment(){dia, mes, año -> onDateSelected(dia, mes, año) }
        datePicker.show(supportFragmentManager,"datePicker")
    }
    public fun onDateSelected(dia:Int,mes:Int,ano:Int){
        desde?.setText(dia+mes+ano)


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