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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.models.InvoiceResponseVO
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
  //  lateinit var listadatos:ArrayList<String>
    //lateinit var recyclerView:androidx.recyclerview.widget.RecyclerView
     lateinit var dato:TextView
    private val Listadatos = mutableListOf<InvoiceResponseVO>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creamos la variable de la toolbar de tipo toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        dato=findViewById(R.id.dato)


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

            if (call.isSuccessful){
                runOnUiThread {
                    /*val aa:ArrayList<String> =listadatos
                    listadatos.addAll(factura.toString())


                   val adapter1:FacturaRecycler= FacturaRecycler(listadatos)
                    recyclerView.adapter=adapter1*/
                    dato.append(factura.toString())
                    Listadatos.clear()
                    Listadatos.addAll(factura)
                    Log.i(TAG_LOGS,Listadatos.size.toString())


                    for(  i in factura.toString()){
                        Log.i(TAG_LOGS,i.toString())
                    }


                    //Log.i(TAG_LOGS,Gson().toJson( factura))

                }
            }else{
                //MOSTRAMOS EL ERROR
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

private fun <E> MutableList<E>.addAll(elements: E?) {

}




