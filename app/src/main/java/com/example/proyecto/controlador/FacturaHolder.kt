package com.example.proyecto.controlador

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemFacturaBinding
import com.example.proyecto.models.*

private val divisa:String="€"


class FacturaHolder(val context: Context, val facturalist: List<InvoiceVO>):
    RecyclerView.Adapter<FacturaHolder.ViewHolder> ()  {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        lateinit var descEstado:TextView
        lateinit var importedeOrdenacion:TextView
        lateinit var fecha:TextView


        val binding=ItemFacturaBinding.bind(itemView)
        init {
            importedeOrdenacion=binding.dato2
            descEstado=binding.dato3
            fecha=binding.dato
            itemView.setOnClickListener {
                val position:Int=adapterPosition
                val intent =Intent(itemView.context,Filtros::class.java)
                val alerta =AlertDialog.Builder(itemView.context)
                alerta.setMessage("Esta funcionalidad aún no está disponible")
                    .setTitle("Información")
                    .setCancelable(false)//esto es para que clique fuera del popup de alerta
                    .setNegativeButton("Cerrar",DialogInterface.OnClickListener { dialog, which ->dialog.cancel()  })
                    .create()
                    .show()

                //startActivity(itemView.context,intent,null)


            }



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
            holder.importedeOrdenacion.text=facturalist[position].importeOrdenacion.toString()+ divisa
        }
        if (facturalist != null) {
            holder.fecha.text=facturalist[position].fecha
        }

        var facturanueva:InvoiceVO
        facturanueva= facturalist[position]






       // Log.i(TAG_LOGS, facturanueva.toString())



    }


    override fun getItemCount(): Int {

        return facturalist!!.size
    }



}


