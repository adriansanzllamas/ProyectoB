package com.example.proyecto.ui.view


import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.example.proyecto.R
import com.example.proyecto.Network.Apiservice
import android.content.Intent
import android.nfc.Tag
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.data.repository
import com.example.proyecto.ui.vistamodelo.mainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

lateinit var service: Apiservice
val TAG_LOGS = "kikopalomares"
lateinit var adapter: FacturaHolder
public val SECOND_ACTIVITY_REQUEST_CODE = 0
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    // se encargara de conectar la activity con nuestro Viewmodel
    private  val mainViewModel: mainViewModel by viewModels()

    @SuppressLint("ResourceType")
    //  lateinit var listadatos:ArrayList<String>
    //lateinit var recyclerView:androidx.recyclerview.widget.RecyclerView



    private var Listadatos = mutableListOf<InvoiceVO?>()


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar = binding.toolbar
        //para poder administar la barra de opciones toolbar.
        setSupportActionBar(toolbar)
        mainViewModel.onCreate()
            Log.i(TAG_LOGS, "comenzando")
            //llamadaRetrofit()

        todasfacturas()

    }
    private fun todasfacturas(){
        mainViewModel.onCreate()
        mainViewModel.facturaslivedata.observe(this){
            Log.i(TAG_LOGS,it.toString())
            if (it != null){
                Listadatos.addAll(it.facturas)
                adapter = FacturaHolder(this@MainActivity, Listadatos)
                val lista = binding.recyclerview
                lista.adapter = adapter
                lista.layoutManager = LinearLayoutManager(this@MainActivity)

            }else{
                Toast.makeText(this,"a habido un eerror",Toast.LENGTH_LONG).show()
            }

        }


    }

/*
    //https://howtodoandroid.com/retrofit-android-example-kotlin/
    private fun llamadaRetrofit() {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
            val factura: InvoiceResponseVO? = call.body()


            //tambn se puede hacer con InvoiceserviceVo en el recyclerview
            val sps = getSharedPreferences("share", MODE_PRIVATE)

            if (call.isSuccessful) {
                runOnUiThread {


                    Listadatos.clear()
                    if (factura != null) {
                        Listadatos.addAll(factura.facturas)
                    }

                    val estadopagada = sps?.getBoolean("pagada", false)
                    val estadopendientedepago = sps?.getBoolean("pendientedepago", false)
                    val estadofecha1 = sps?.getString("fecha1", "")
                    val estadofecha2=sps?.getString("fecha2","")
                    val estadobarra=sps?.getString("barra","")


                    if (numero != 0) {//para que no pinte las preferencias ya marcadas anteriormente
                        //ponemos primero los check box porque son los que determinan con mayor fuerza las facturas
                        //que quieres filtrar de lo contrario se borrarian y no harian bien el filtrado. y se perderian datos.
                        val formato = SimpleDateFormat("dd/MM/yyyy")
                        if (estadopagada == true) {
                            Listadatos.removeAll { it!!.descEstado != "Pagada" }
                        }
                        if (estadopendientedepago == true) {
                            Listadatos.removeAll { it!!.descEstado != "Pendiente de pago" }
                        }
                        if ((estadofecha1 != "") && (estadofecha2=="")) {
                            Log.i(TAG_LOGS,estadofecha1.toString())
                            Listadatos.removeAll { it!!.fecha!=(estadofecha1!!)}
                        }
                        if (estadofecha2 != ""&&estadofecha1=="") {
                            Log.i(TAG_LOGS,estadofecha1.toString())
                            Listadatos.removeAll { (it!!.fecha)!=estadofecha2!! }
                        }

                        if (estadofecha1 != "" && estadofecha2 != "") {
                            Listadatos.removeAll {
                                formato.parse(it!!.fecha).before(formato.parse(estadofecha1!!))
                            }
                            Listadatos.removeAll {
                                formato.parse(it!!.fecha).after(formato.parse(estadofecha2!!))
                            }

                            Log.i(TAG_LOGS, Listadatos.toString())
                        }

                        if (estadobarra!=""){

                            val barra: Double = estadobarra!!.toDouble()
                            Listadatos.removeAll { it!!.importeOrdenacion >=barra }
                        }



                    }

                    adapter = FacturaHolder(this@MainActivity, Listadatos)

                    val lista = binding.recyclerview
                    lista.adapter = adapter
                    lista.layoutManager = LinearLayoutManager(this@MainActivity)


                }
            } else {
                Toast.makeText(this@MainActivity,"No hay datos",Toast.LENGTH_LONG).show()
            }


        }


    }*/


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
                startActivityForResult(intent,0)

                return true
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG_LOGS, "start")
        //llamadaRetrofit()


    }

    override fun onResume() {
        super.onResume()
        //llamadaRetrofit()
        mainViewModel.onCreate()

        mainViewModel.facturaslivedata.observe(this){
            Log.i(TAG_LOGS,it.toString())

            adapter = FacturaHolder(this@MainActivity, it!!.facturas)

            val lista = binding.recyclerview
            lista.adapter = adapter
            lista.layoutManager = LinearLayoutManager(this@MainActivity)

        }



    }

    override fun onRestart() {
        super.onRestart()


    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_LOGS, "pausado")

    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_LOGS, "parado")


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_LOGS, "destruido")
    }

}










