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
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.Network.InvoiceService
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.models.InvoiceResponseVO
import com.example.proyecto.models.InvoiceVO
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    }
    //https://howtodoandroid.com/retrofit-android-example-kotlin/
    private fun LlamadaRetrofit(){
      val apiInterface:Apiservice=RetrofitHelper().getRetrofit().create(Apiservice::class.java)
        apiInterface.getAllFacturas().enqueue(object :Callback<InvoiceResponseVO>{
            override fun onResponse(
                call: Call<InvoiceResponseVO>,
                response: Response<InvoiceResponseVO>
            ) {
                val body=response.body()

            }

            override fun onFailure(call: Call<InvoiceResponseVO>, t: Throwable) {
                Toast.makeText(this@MainActivity,"errror",Toast.LENGTH_LONG).show()
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


