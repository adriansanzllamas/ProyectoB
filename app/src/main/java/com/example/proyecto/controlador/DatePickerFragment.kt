package com.example.proyecto.controlador

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

//esta clase extiende del dialogfragment que es otra clase de android que muestra los diaologos con los que queremos trabajar.
class DatePickerFragment(val listener:(dia:Int,mes:Int,año:Int)->Unit):DialogFragment(),DatePickerDialog.OnDateSetListener {


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //este metodo nos va a devolver dia mes año
        listener(dayOfMonth,month+1,year)//ponemos mas uno para que no omita el mes que esta seleccionado de manera predeterminada
    }

//esta funcion es para configurar el calendario con la instacia del dia en el que se encuentre.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c= Calendar.getInstance()
        val dia=c.get(Calendar.DAY_OF_MONTH)
        val mes=c.get(Calendar.MONTH)
        val year=c.get(Calendar.YEAR)


        val picker =DatePickerDialog(activity as Context,this,year,mes,dia)
    c.add(Calendar.YEAR, -20)
    picker.datePicker.minDate=c.timeInMillis



        return picker
    }
}