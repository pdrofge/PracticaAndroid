package com.example.practicaandroid.data_retrofit

import com.example.practicaandroid.domain.model.FacturaRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://d5cbc2f0-226a-41a7-8baa-83e4b5104c5e.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: FacturasApi by lazy {
        retrofit.create(FacturasApi::class.java)
    }

    val repository: FacturaRepository by lazy {
        FacturaRepositoryImpl(api)
    }
}
