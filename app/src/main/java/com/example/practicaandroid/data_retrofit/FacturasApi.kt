package com.example.practicaandroid.data_retrofit

import retrofit2.http.GET

interface FacturasApi {
    @GET("facturas")
    suspend fun getFacturas(): FacturasResponse
}