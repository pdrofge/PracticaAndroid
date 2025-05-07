package com.example.practicaandroid.core.screens.SmartSolar.Detalles

object DatosDetallesMock {

    fun getMock(): DatosDetalles {
        return DatosDetalles(
            CAU = "ES0021000000001994LJ1FA000",
            solicitud = "No hemos recibido ninguna solicitud de autoconsumo",
            tipo = "Con excedentes y compensación Individual - Consumo",
            compensacion = "Precio PVPC",
            potencia = "5 kWp"
        )
    }


}