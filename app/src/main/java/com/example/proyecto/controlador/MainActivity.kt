package com.example.proyecto.controlador


import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.proyecto.R
import com.example.proyecto.Network.Apiservice
import android.content.Intent
import android.nfc.Tag
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.models.InvoiceResponseVO
import com.example.proyecto.models.InvoiceVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    //  lateinit var listadatos:ArrayList<String>
    //lateinit var recyclerView:androidx.recyclerview.widget.RecyclerView

    private var Listadatos = mutableListOf<InvoiceVO?>()
    lateinit var adapter: FacturaHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar = binding.toolbar
        //para poder administar la barra de opciones toolbar.
        setSupportActionBar(toolbar)

        llamadaRetrofit()



    }

    //https://howtodoandroid.com/retrofit-android-example-kotlin/
    private fun llamadaRetrofit() {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
            val factura: InvoiceResponseVO? = call.body()


            //tambn se puede hacer con InvoiceserviceVo en el recyclerview

            if (call.isSuccessful) {
                runOnUiThread {


                    Listadatos.clear()
                    if (factura != null) {
                        Listadatos.addAll(factura.facturas)
                    }

                    if (estadopagada!!.equals(true)){
                        Listadatos.removeAll { it!!.descEstado!="Pagada" }
                    }
                    if (estadopendientedepago!!.equals(true)){
                        Listadatos.removeAll { it!!.descEstado!="Pendiente de pago" }
                    }




                    adapter = FacturaHolder(this@MainActivity, Listadatos)

                    val lista = binding.recyclerview
                    lista.adapter = adapter
                    lista.layoutManager = LinearLayoutManager(this@MainActivity)


                }
            } else {
                Toast.makeText(this@MainActivity, "no hay datos", Toast.LENGTH_LONG).show()
            }


        }


    }


    public fun initRecycleview() {
// adapter=RecyclerView(FacturAS)
        // var recycler:androidx.recyclerview.widget.RecyclerView=findViewById(R.id.recyclerview)


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
                estadopagada=false
                estadopendientedepago=false



                return true
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG_LOGS,"start")
        llamadaRetrofit()


    }

    override fun onResume() {
        super.onResume()




    }

    override fun onRestart() {
        super.onRestart()



    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_LOGS,"pausado")

    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_LOGS,"parado")


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_LOGS,"destruido")
    }

}










