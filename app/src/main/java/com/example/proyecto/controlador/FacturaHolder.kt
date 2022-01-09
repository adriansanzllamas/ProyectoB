package com.example.proyecto.controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemFacturaBinding
import com.example.proyecto.models.*

class FacturaHolder(val context: Context, val facturalist: List<InvoiceVO>?):
    RecyclerView.Adapter<FacturaHolder.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        lateinit var descEstado:TextView
        lateinit var importedeOrdenacion:TextView
        lateinit var fecha:TextView
        private val divisa:String="€"
        val binding=ItemFacturaBinding.bind(itemView)
        init {

            importedeOrdenacion=binding.dato2
            descEstado=binding.dato3
            fecha=binding.dato

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var itemView=LayoutInflater.from(context).inflate(R.layout.item_factura,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (facturalist != null) {
            holder.descEstado.text= facturalist[position].descEstado
        }
        if (facturalist != null) {
            holder.importedeOrdenacion.text=facturalist[position].importeOrdenacion.toString()
        }
        if (facturalist != null) {
            holder.fecha.text=facturalist[position].fecha
        }


    }

    override fun getItemCount(): Int {

        return facturalist!!.size
    }


}

