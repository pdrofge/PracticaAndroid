package com.example.practicaandroid.data_retrofit

import com.example.practicaandroid.domain.model.Factura

data class FacturasResponse(
    val numFacturas : Int,
    val facturas: List<Factura>
){
    companion object{
        val listaFacturas :List<Factura> = listOf(
            Factura("Pendiente de pago", 1.5600000000000001, "07/02/2019"),
            Factura("Pagada",25.140000000000001, "05/02/2019"),
            Factura("Pagada",22.690000000000001, "08/01/2019"),
            Factura("Pendiente de pago", 12.84, "07/12/2018"),
            Factura("Pendiente de pago", 12.84, "07/12/2018"),
            Factura("Pendiente de pago", 12.84, "07/12/2018"),
            Factura("Pendiente de pago", 12.84, "07/12/2018"),
            Factura("Pendiente de pago", 12.84, "07/12/2018"))
        val listaEstatica = FacturasResponse(8,
            listaFacturas)
    }

}
