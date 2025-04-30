package com.example.practicaandroid.core.screens.Filtros

import com.example.practicaandroid.domain.model.Factura
import java.text.SimpleDateFormat
import java.util.*

fun Filtrar(listaFacturas: List<Factura>, filtros: FiltrosFinales) : List<Factura> {




      val listaFiltrada: MutableList<Factura> = mutableListOf()




     for(factura in listaFacturas){
        val fecha = filtrarFecha(factura, filtros.startDate, filtros.endDate)
        val importe = filtrarImporte(factura, filtros.minAmount,  filtros.maxAmount)
        val estado = filtrarEstado(factura, filtros.isPaid, filtros.isPaymentPlan, filtros.hasToPay, filtros.isFixed, filtros.isCancelled)

        if(fecha && importe && estado){
           listaFiltrada.add(factura)
        }
     }


        return listaFiltrada

}

fun filtrarFecha(factura: Factura, startDate: String, endDate: String): Boolean{

   var fechaFactura : Long = 0L
   var startDateTiempo : Long = 0L
   var endDateTiempo : Long = 0L




   if(!factura.fecha.isEmpty()){ fechaFactura  = fechaATiempo(factura.fecha)}
   if(!startDate.isEmpty()){ startDateTiempo = fechaATiempo(startDate)}
   if(!endDate.isEmpty()){ endDateTiempo =    fechaATiempo(endDate)}
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
   //falla al no seleccionar ninguno
      var res: Boolean = true
   if((paid && !factura.decEstado.equals("Pagada"))
      || (hasToPay && !factura.decEstado.equals("Pendiente de pago"))
      || (paymentPlan && !factura.decEstado.equals("Plan de pago"))
      || (fixed && !factura.decEstado.equals("Fijo"))
      || (cancelled && !factura.decEstado.equals("Cancelado"))
      ){
         res = false
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