package com.example.proyecto.controlador

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

//esta clase extiende del dialogfragment que es otra clase de android que muestra los diaologos con los que queremos trabajar.
class DatePickerFragment(val listener:(dia:Int,mes:Int,año:Int)->Unit):DialogFragment(),DatePickerDialog.OnDateSetListener {


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //este metodo nos va a devolver dia mes año
        listener(dayOfMonth,month,year)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c= Calendar.getInstance()
        val dia=c.get(Calendar.DAY_OF_MONTH)
        val mes=c.get(Calendar.MONTH)
        val year=c.get(Calendar.YEAR)

        val picker =DatePickerDialog(activity as Context,this,year,mes,dia)
        return picker
    }
}