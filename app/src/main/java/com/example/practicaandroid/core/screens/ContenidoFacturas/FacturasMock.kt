package com.example.practicaandroid.core.screens.ContenidoFacturas

import com.example.practicaandroid.core.screens.SmartSolar.Detalles.DatosDetalles
import com.example.practicaandroid.data_retrofit.FacturasResponse
import com.example.practicaandroid.domain.model.Factura

object FacturasMock {
    fun getMock(): FacturasResponse {
        val facturas = listOf(
            Factura("Pagada", 120.50, "2024/12/01"),
            Factura("Pendiente de pago", 89.99, "2025/01/15"),
            Factura("Pagada", 45.00, "2025/02/10"),
            Factura("Cancelado", 230.75, "2025/03/05"),
            Factura("Pendiente de pago", 150.25, "2025/04/20")
        )
        return FacturasResponse(5, facturas)
    }
}