package com.example.proyecto.controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.models.*

class FacturaHolder(val context: Context, val facturalist: InvoiceResponseVO?):
    RecyclerView.Adapter<FacturaHolder.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        lateinit var descEstado:TextView
        lateinit var importedeOrdenacion:TextView
        lateinit var fecha:TextView
        init {
            importedeOrdenacion=itemView.findViewById(R.id.dato2)
            descEstado=itemView.findViewById(R.id.dato3)
            fecha=itemView.findViewById(R.id.dato)

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var itemView=LayoutInflater.from(context).inflate(R.layout.item_factura,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (facturalist != null) {
            holder.descEstado.text= facturalist.facturas[position].descEstado
        }
        if (facturalist != null) {
            holder.importedeOrdenacion.text=facturalist.facturas[position].importeOrdenacion.toString()
        }
        if (facturalist != null) {
            holder.fecha.text=facturalist.facturas[position].fecha
        }


    }

    override fun getItemCount(): Int {

        return facturalist!!.facturas.size
    }


}

