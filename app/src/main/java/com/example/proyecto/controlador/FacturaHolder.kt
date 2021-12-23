package com.example.proyecto.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.models.InvoiceResponseVO

class FacturaHolder(val facturas:List<InvoiceResponseVO>):RecyclerView.Adapter<FacturaHolder.ViewHolder>(){

class ViewHolder(view:View):RecyclerView.ViewHolder(view){

    fun bind(facturas: InvoiceResponseVO) {


    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_factura,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val factura=facturas[position]
        holder.bind(factura)

    }

    override fun getItemCount(): Int {
        return facturas.size

    }
}