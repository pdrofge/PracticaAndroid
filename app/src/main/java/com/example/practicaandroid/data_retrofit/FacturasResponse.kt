package com.example.practicaandroid.data_retrofit

import com.example.practicaandroid.domain.model.Factura

data class FacturasResponse(
    val numFacturas : Int,
    val facturas: List<Factura>
)
