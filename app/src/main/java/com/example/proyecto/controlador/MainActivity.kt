package com.example.proyecto.controlador


import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.proyecto.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.proyecto.models.Apiservice
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.controlador.Filtros
import com.example.proyecto.models.Facturas
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"

class MainActivity : AppCompatActivity() {
    var texto: TextView? = null
    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        texto = findViewById(R.id.textView)
        setSupportActionBar(toolbar)
        //MenuItem menuItem = findViewById(R.menu.menu);



//esto es un comentario

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://viewnextandroid.mocklab.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<Apiservice>(Apiservice::class.java)

getAllPosts()

    }
    fun getAllPosts(){



    }


    //creamos el menu para que los elemntos esten visibles.
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