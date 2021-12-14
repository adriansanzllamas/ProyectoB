package com.example.proyecto.controlador


import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.proyecto.R
import com.example.proyecto.models.Apiservice
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.models.FacturaResponse
import com.example.proyecto.models.Facturaindividual
import com.google.gson.Gson

import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import java.util.*
import java.util.logging.Level.INFO

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
        LlamadaRetrofit()











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

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://viewnextandroid.mocklab.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //creamos el servivio para hacer las llamadas


        val service= retrofit.create<Apiservice>(Apiservice::class.java)
      service.getAllFacturas().enqueue(object :Callback<FacturaResponse>{
          override fun onResponse(
              call: Call<FacturaResponse>, response: Response<FacturaResponse>
          ) {
              if(response.isSuccessful){
                  texto.append(response.body().toString())

              }else{
                 Toast.makeText(this@MainActivity,"ha habido un error",Toast.LENGTH_LONG).show()
              }

              Log.i(TAG_LOGS,Gson().toJson(response.body().toString()))


          }

          override fun onFailure(call: Call<FacturaResponse>, t: Throwable) {
              TODO("Not yet implemented")
          }

      })

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


