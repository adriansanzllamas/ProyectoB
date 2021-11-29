package com.example.proyecto;

import com.example.proyecto.models.Facturas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface llamada {
    @GET("facturas")
    Call<Facturas> getData();

}
