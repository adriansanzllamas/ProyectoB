package com.example.proyecto.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;

import java.util.ArrayList;

public class FacturaRecycler extends RecyclerView.Adapter<FacturaRecycler.ViewHolderDatos> {

ArrayList<String>listadatos;

    public FacturaRecycler(ArrayList<String> listadatos) {
        this.listadatos = listadatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_factura,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
holder.asignardatos(listadatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
TextView dato;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato=itemView.findViewById(R.id.dato);

        }

        public void asignardatos(String s) {
            dato.setText(s);
        }
    }
}

