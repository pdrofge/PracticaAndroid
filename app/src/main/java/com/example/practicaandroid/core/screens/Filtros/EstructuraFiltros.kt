package com.example.practicaandroid.core.screens.Filtros

import com.example.practicaandroid.domain.model.Factura

data class EstructuraFiltros(
    //para fecha inicio-fin
    val listaFacturas: List<Factura>,
    val startDate: String = "",
    val endDate: String = "",
    //para slider
    val minAmount: Double = minPrice(listaFacturas),
    val maxAmount: Double = maxPrice(listaFacturas),
    //para las checkboxes
    val isPaid: Boolean = false,
    val isCancelled: Boolean = false,
    val isFixed: Boolean = false,
    val hasToPay: Boolean = false,
    val isPaymentPlan: Boolean = false
)



fun maxPrice(listaFacturas: List<Factura>):Double{
    var max = listaFacturas.get(0).importeOrdenacion
    for(factura in listaFacturas){
        val actual = factura.importeOrdenacion
        if(actual > max)max = actual
    }

    return max
}

fun minPrice(listaFacturas: List<Factura>):Double{
    var min = listaFacturas.get(0).importeOrdenacion
    for(factura in listaFacturas){
        val actual = factura.importeOrdenacion
        if(actual < min)min = actual
    }

    return min
}
