package com.example.practicaandroid.core.screens.Filtros

import com.example.practicaandroid.domain.model.Factura
import java.text.SimpleDateFormat
import java.util.*

fun Filtrar(listaFacturas: List<Factura>, filtros: FiltrosFinales) : List<Factura> {




      val listaFiltrada: MutableList<Factura> = mutableListOf()




     for(factura in listaFacturas){
        if(filtrarFecha(factura, filtros.startDate, filtros.endDate) && filtrarImporte(factura, filtros.minAmount,  filtros.maxAmount) &&
           filtrarEstado(factura, filtros.isPaid, filtros.isPaymentPlan, filtros.hasToPay, filtros.isFixed, filtros.isCancelled)){
           listaFiltrada.add(factura)
        }
     }


        return listaFiltrada

}

fun filtrarFecha(factura: Factura, startDate: String, endDate: String): Boolean{
   //Prasea fechas vacías, arreglar
   val fechaFactura = fechaATiempo(factura.fecha)
   val startDateTiempo = fechaATiempo(startDate)
   val endDateTiempo =    fechaATiempo(endDate)
   val despuesDeStart = startDate.isEmpty() || fechaFactura >= startDateTiempo
   val antesDeEnd = endDate.isEmpty() || fechaFactura <= endDateTiempo

   return despuesDeStart && antesDeEnd
}

fun filtrarEstado(
   factura: Factura,
   paid: Boolean,
   paymentPlan: Boolean,
   hasToPay: Boolean,
   fixed: Boolean,
   cancelled: Boolean
): Boolean{
      var res: Boolean = false
   if((paid && factura.decEstado.equals("Pagada"))
      || (hasToPay && factura.decEstado.equals("Pendiente de pago"))
      || (paymentPlan && factura.decEstado.equals("Plan de pago"))
      || (fixed && factura.decEstado.equals("Fijo"))
      || (cancelled && factura.decEstado.equals("Cancelado"))
      ){
         res = true
   }
   return res
}

fun filtrarImporte(factura: Factura, minAmount: Double, maxAmount: Double): Boolean{
   var res : Boolean = false
   if(factura.importeOrdenacion > minAmount && factura.importeOrdenacion < maxAmount) {
      res = true
   }
   return res
}

fun fechaATiempo(fecha: String): Long {
   val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
   val date = formato.parse(fecha)
   return date?.time ?: 0L
}