package com.example.proyecto.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.models.InvoiceResponseVO

class RecyclerView(val facturasrecycler:List<InvoiceResponseVO>):RecyclerView.Adapter<FacturaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaHolder {
        val layoutinflater=LayoutInflater.from(parent.context)
        return  FacturaHolder(layoutinflater.inflate(R.layout.item_factura,parent,false))
    }

    override fun onBindViewHolder(holder: FacturaHolder, position: Int) {
    val itemposition:InvoiceResponseVO=facturasrecycler[position]
        holder.bind(itemposition)
    }

    override fun getItemCount(): Int {
       return facturasrecycler.size
    }


//este metodo le va a decir al recycler view cuantos elementos tiene





}
