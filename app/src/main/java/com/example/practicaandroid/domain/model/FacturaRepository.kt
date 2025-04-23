package com.example.practicaandroid.domain.model

interface FacturaRepository {
    suspend fun getFacturas(): List<Factura>
}