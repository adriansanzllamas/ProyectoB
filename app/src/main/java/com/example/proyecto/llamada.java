package com.example.proyecto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface llamada {
    @GET("facturas")
    Call<Facturas> getData();

}
