package com.example.proyecto.controlador


import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.proyecto.R
import com.example.proyecto.Network.Apiservice
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.Network.InvoiceService
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.models.InvoiceVO
import com.google.gson.Gson

lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"



class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    lateinit var texto: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //val diamesaño2:Button=findViewById(R.id.diamesaño2)
         texto = findViewById(R.id.textView)
        //para poder administar la barra de opciones toolbar.
        setSupportActionBar(toolbar)
        //LlamadaRetrofit()

















           /* val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://viewnextandroid.mocklab.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create<Apiservice>(Apiservice::class.java)
        service.getAllFacturas().enqueue(object :Callback<List<Facturas>>{
            override fun onResponse(call: Call<List<Facturas>>, response: Response<List<Facturas>>) {


            }

            override fun onFailure(call: Call <List<Facturas>>, t: Throwable) {

            }

        })*/




    }
    private fun LlamadaRetrofit(){




    }

    //creamos el menu para que los elemntos esten visibles.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
//las funciones para mostrar los elementos del menu y su funcionalidad
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


