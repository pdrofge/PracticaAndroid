package com.example.practicaandroid.core.screens.Filtros

import com.example.practicaandroid.domain.model.Factura


fun Filtrar(listaFacturas: List<Factura>, filtros: FiltrosFinales) : List<Factura> {

    //cambiar más adelante por filtrado
     val listaFacturas :List<Factura> = listOf(
        Factura("Pendiente de pago", 1.5600000000000001, "07/02/2019"),
        Factura("Pagada",25.140000000000001, "05/02/2019"),
        Factura("Pagada",22.690000000000001, "08/01/2019"),
        Factura("Pendiente de pago", 12.84, "07/12/2018"),
        Factura("Pendiente de pago", 12.84, "07/12/2018"),
        Factura("Pendiente de pago", 12.84, "07/12/2018"),
        Factura("Pendiente de pago", 12.84, "07/12/2018"),
        Factura("Pendiente de pago", 12.84, "07/12/2018"))


        return listaFacturas

}