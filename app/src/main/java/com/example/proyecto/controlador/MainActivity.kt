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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.models.InvoiceResponseVO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
  //  lateinit var listadatos:ArrayList<String>
    //lateinit var recyclerView:androidx.recyclerview.widget.RecyclerView

    private val Listadatos = ArrayList<InvoiceResponseVO?>()


    lateinit var adapter: FacturaHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar =binding.toolbar



        //para poder administar la barra de opciones toolbar.
        setSupportActionBar(toolbar)

        llamadaRetrofit()
/*recyclerView =findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)*/

    }
    //https://howtodoandroid.com/retrofit-android-example-kotlin/
    private fun llamadaRetrofit(){
        CoroutineScope(Dispatchers.IO).launch {
            val call=RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas().execute()
            val factura: InvoiceResponseVO? = call.body()

            adapter= FacturaHolder(this@MainActivity,factura!!.facturas)//tambn se puede hacer con InvoiceserviceVo en el recyclerview



            if (call.isSuccessful){
                runOnUiThread {

                    Listadatos.clear()
                    Listadatos.addAll(listOf(factura))
                    factura!!.facturas[1].importeOrdenacion

                    val lista=binding.recyclerview
                    lista.adapter=adapter
                    lista.layoutManager=LinearLayoutManager(this@MainActivity)

                }
            }else{
              Toast.makeText(this@MainActivity,"no hay datos",Toast.LENGTH_LONG).show()
            }

        }


    }


    public fun initRecycleview(){
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

               return true
            }
        }
        return true
    }


}










