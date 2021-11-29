package com.example.proyecto.models;

import java.util.Date;

public class Facturas {
    private String descEstado;
    private Float importeOrdenacion;
    private Date fecha;

    public Facturas(String descEstado, Float importeOrdenacion, Date fecha) {
        this.descEstado = descEstado;
        this.importeOrdenacion = importeOrdenacion;
        this.fecha = fecha;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public Float getImporteOrdenacion() {
        return importeOrdenacion;
    }

    public void setImporteOrdenacion(Float importeOrdenacion) {
        this.importeOrdenacion = importeOrdenacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
