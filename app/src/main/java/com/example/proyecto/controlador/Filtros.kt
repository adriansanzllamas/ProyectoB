package com.example.proyecto.controlador

import android.app.AlertDialog
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

//https://www.youtube.com/watch?v=-GGcrlaEWUw

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

        lateinit var rangeSeekBar: RangeSeekBar<Int>
        rangeSeekBar = binding.rangeseekbar as RangeSeekBar<Int>
        rangeSeekBar.setRangeValues(9, 100)
        rangeSeekBar.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener<Int> {
            override fun onRangeSeekBarValuesChanged(
                bar: RangeSeekBar<*>?,
                minValue: Int,
                maxValue: Int
            ) {
                //  //Now you have the minValue and maxValue of your RangeSeekbar
                // Toast.makeText(getActivity(), minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
                volumen.setText("$minValue-$maxValue")
            }

        })



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
            //se aplicarian los filtros
        }
        borrar.setOnClickListener {
            pagadas.isChecked = false
            pendientedepago.isChecked = false
            plandepago.isChecked = false
            cuotafija.isChecked = false
            anuladas.isChecked = false
            binding.importe.setText("")
            rangeSeekBar.setRangeValues(9, 100)
            if (fechaescrita1 == true || fechaescrita2 == true) {
                binding.diamesaO1.setText("")
                binding.diamesaO2.setText("")
            }


        }
        //obtener el intent de la anterior clase o pantalla
        //val intent = intent

    }

    public fun showDatePickerDialog() {
        val datePicker = DatePickerFragment({ dia, mes, año -> onDateSelected(dia, mes, año) })
        datePicker.show(supportFragmentManager, "datePicker")


    }

    public fun onDateSelected(dia: Int, mes: Int, ano: Int) {

        if (botonactivado1 == true && botonactivado2 == false) {
            diamesaño1?.setText(" $dia/$mes/$ano")

            // botonactivado2==true
        } else {
            diamesaño2?.setText(" $dia/$mes/$ano")

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
                finish()
                return true
            }
        }
        return true
    }


}


