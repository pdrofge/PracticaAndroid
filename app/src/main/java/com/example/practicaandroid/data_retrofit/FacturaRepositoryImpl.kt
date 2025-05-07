package com.example.practicaandroid.data_retrofit

import com.example.practicaandroid.domain.model.Factura
import com.example.practicaandroid.domain.model.FacturaRepository

class FacturaRepositoryImpl(
    private val api: FacturasApi
) : FacturaRepository {
    override suspend fun getFacturas(): List<Factura> {
        return api.getFacturas().facturas
    }
}