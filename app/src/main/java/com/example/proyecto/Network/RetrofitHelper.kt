package com.example.proyecto.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {
        private const val BASE_URL = "https://viewnextandroid.mocklab.io/"
    }
}
