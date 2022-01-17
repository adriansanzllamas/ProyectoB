package com.example.proyecto.controlador

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto.R
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Network.Apiservice
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.controlador.Filtros
import com.example.proyecto.databinding.ActivityFiltrosBinding
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.models.InvoiceResponseVO
import com.example.proyecto.models.InvoiceVO
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.bendik.simplerangeview.SimpleRangeView
import org.florescu.android.rangeseekbar.RangeSeekBar
import java.util.*
import android.content.SharedPreferences
import android.os.PersistableBundle
import android.preference.PreferenceManager
import java.text.SimpleDateFormat


public var estadofecha2: String = ""

public var guaradrx:Int=0
public var numero: Int =
    0//esta variable es para que no se inicien las preferencias directamente hasta que no entre y las haga

//https://www.youtube.com/watch?v=-GGcrlaEWUw

public val estadopagada: Boolean = false

class Filtros : AppCompatActivity() {
    private lateinit var binding: ActivityFiltrosBinding
    val TAG_LOGS = "kikopalomares"
    private var diamesaño1: Button? = null
    private var diamesaño2: Button? = null
    private var desde: TextView? = null
    private var hasta: TextView? = null
    private var botonactivado1: Boolean = false
    private var botonactivado2: Boolean = false
    private lateinit var buscador: SearchView
    private lateinit var pagadas: CheckBox
    private lateinit var pendientedepago: CheckBox
    private lateinit var anuladas: CheckBox
    private lateinit var cuotafija: CheckBox
    private lateinit var plandepago: CheckBox
    lateinit var adapter: FacturaHolder
    private lateinit var aplicar: Button
    private lateinit var borrar: Button
    private var fechaescrita1: Boolean = false
    private var fechaescrita2: Boolean = false
    var fechaestado: String=""
    var fechaestado2: String=""


    val sb: StringBuilder = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityFiltrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar1 = binding.toolbar1
        setSupportActionBar(toolbar1)

        diamesaño1 = binding.diamesaO1
        diamesaño2 = binding.diamesaO2
        desde = binding.desde
        hasta = binding.hasta
        pagadas = binding.pagadas
        pendientedepago = binding.pendientedepago
        aplicar = binding.aplicarfiltros
        borrar = binding.borrarfiltros
        anuladas = binding.anuladas
        cuotafija = binding.cuotafija
        plandepago = binding.plandepago


        var volumen: TextView = binding.importe


        lateinit var rangeSeekBar: SeekBar
        rangeSeekBar = binding.rangeseekbar
        rangeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val decimalProgress = progress.toDouble()
                volumen.text = (decimalProgress.toString())


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        if(guaradrx==1){
            val sps = getSharedPreferences("share", MODE_PRIVATE)
            val estadopagada = sps?.getBoolean("pagada", false)
            val estadopendientedepago = sps?.getBoolean("pendientedepago", false)
            val estadofecha1 = sps?.getString("fecha1", "")
            val estadofecha2=sps?.getString("fecha2","")
            val estadobarra=sps?.getString("barra","")
            if(estadofecha1!=""){
                Log.i(TAG_LOGS,estadofecha1.toString())
                diamesaño1!!.text=estadofecha1
            }else{
                diamesaño1!!.text=""
            }
            if(estadopagada!=false){
                pagadas.isChecked=true
            }else{
                pagadas.isChecked=false
            }
            if(estadopendientedepago!=false){
                pendientedepago.isChecked=true
            }else{
                pendientedepago.isChecked=false
            }

            if(estadofecha2!=""){
                diamesaño2!!.text=estadofecha2
            }else{
                diamesaño1!!.text=""
            }


        }


        diamesaño1?.setOnClickListener {

            showDatePickerDialog()
            fechaescrita1 = true
            botonactivado1 = true


        }

        diamesaño2?.setOnClickListener {
            showDatePickerDialog()
            fechaescrita2 = true
            botonactivado2 = true
        }


        aplicar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            numero = 1
            /*if (pagadas.isChecked){
                estadopagada=true
            }else{
                estadopagada==false
            }
            if (pendientedepago.isChecked){
                estadopendientedepago=true
            }else{
                estadopendientedepago==false
            }
            if(binding.diamesaO1.text!=""){

                binding.diamesaO1.text= estadofecha
                Log.i(TAG_LOGS, estadofecha)
            }else{
                estadofecha=""
            }*/
            val sps = getSharedPreferences("share", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sps.edit()
            editor.putBoolean("pagada", pagadas.isChecked)
            editor.putBoolean("pendientedepago", pendientedepago.isChecked)
            editor.putString("barra", volumen.text.toString())
            editor.putString("fecha1", fechaestado)
            editor.putString("fecha2", fechaestado2)

            editor.apply()
            setResult(Activity.RESULT_OK, intent)
            guaradrx=1

            finish()


        }
        borrar.setOnClickListener {
            pagadas.isChecked = false
            pendientedepago.isChecked = false
            plandepago.isChecked = false
            cuotafija.isChecked = false
            anuladas.isChecked = false
            binding.importe.setText("")
            //barra
            if (diamesaño1!!.text!="" || diamesaño2!!.text!="") {
                binding.diamesaO1.setText("")
                binding.diamesaO2.setText("")
            }


        }


    }

    public fun showDatePickerDialog() {
        val datePicker = DatePickerFragment({ dia, mes, año -> onDateSelected(dia, mes, año) })
        datePicker.show(supportFragmentManager, "datePicker")


    }

    public fun onDateSelected(dia: Int, mes: Int, ano: Int) {

        if (botonactivado1 == true && botonactivado2 == false) {

            if ((mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) && (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 || dia == 6 || dia == 7 || dia == 8 || dia == 9)) {
                diamesaño1?.setText("0$dia/0$mes/$ano")
                fechaestado = "0$dia/0$mes/$ano"

            } else if ((mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) && (dia != 1 || dia != 2 || dia != 3 || dia != 4 || dia != 5 || dia != 6 || dia != 7 || dia != 8 || dia != 9)) {
                diamesaño1?.setText("$dia/0$mes/$ano")
                fechaestado = "$dia/0$mes/$ano"
            } else if ((mes != 1 || mes != 2 || mes != 3 || mes != 4 || mes != 5 || mes != 6 || mes != 7 || mes != 8 || mes != 9) && (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 || dia == 6 || dia == 7 || dia == 8 || dia == 9)) {
                diamesaño1?.setText("0$dia/$mes/$ano")
                fechaestado = "0$dia/$mes/$ano"

            } else {
                diamesaño1?.setText("$dia/$mes/$ano")
                fechaestado = "$dia/$mes/$ano"


            }

            // botonactivado2==true
        } else {

            if ((mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) && (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 || dia == 6 || dia == 7 || dia == 8 || dia == 9)) {
                diamesaño2?.setText("0$dia/0$mes/$ano")
                fechaestado2 = "0$dia/0$mes/$ano"


            } else if ((mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) && (dia != 1 || dia != 2 || dia != 3 || dia != 4 || dia != 5 || dia != 6 || dia != 7 || dia != 8 || dia != 9)) {
                diamesaño2?.setText("$dia/0$mes/$ano")
                fechaestado2 = "$dia/0$mes/$ano"

            } else if ((mes != 1 || mes != 2 || mes != 3 || mes != 4 || mes != 5 || mes != 6 || mes != 7 || mes != 8 || mes != 9) && (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 || dia == 6 || dia == 7 || dia == 8 || dia == 9)) {
                diamesaño2?.setText("0$dia/$mes/$ano")
                fechaestado2 = "0$dia/$mes/$ano"

            } else {
                diamesaño2?.setText("$dia/$mes/$ano")
                fechaestado2 = "$dia/$mes/$ano"
            }

            // botonactivado1==true
        }
        botonactivado1 = false
        botonactivado2 = false


    }


    //mostrar el menu con la toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menufiltros, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //funcion para mostrar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion2 -> {
                val intent = Intent(this, MainActivity::class.java)
               guaradrx=1

                finish()//no se superpongan las activities

                return true
            }
        }
        return true
    }


    override fun onStart() {
        super.onStart()


    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        /// finish()

    }


}









