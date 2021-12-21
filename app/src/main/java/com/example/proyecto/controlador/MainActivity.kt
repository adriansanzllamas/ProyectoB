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
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.models.InvoiceVO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"



class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    lateinit var texto: TextView
    private val FacturAS = mutableListOf<InvoiceVO>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //val diamesaño2:Button=findViewById(R.id.diamesaño2)

        //para poder administar la barra de opciones toolbar.
        setSupportActionBar(toolbar)
        LlamadaRetrofit()


    }
    //https://howtodoandroid.com/retrofit-android-example-kotlin/
    private fun LlamadaRetrofit(){
        CoroutineScope(Dispatchers.IO).launch {
            val call=RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas().execute()
            val factura: InvoiceVO? = call.body()

            if (call.isSuccessful){
                runOnUiThread {
                    //val datos=factura?.copy()

                    FacturAS.clear()
                   // FacturAS.addAll()
                    Log.i(TAG_LOGS,Gson().toJson( factura))
                }
            }else{
                //MOSTRAMOS EL ERROR
            }

        }


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




