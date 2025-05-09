package com.example.practicaandroid.data_room

import com.example.practicaandroid.domain.model.Factura

class FacturaDBRepository(
    private val facturaDao: FacturaDao
) {

    suspend fun getUsers() : List<Factura>{
        return facturaDao.getFacturas()
    }

    suspend fun insertfacturas(factura: Factura){
        val entity = Factura(descEstado = factura.descEstado, importeOrdenacion = factura.importeOrdenacion, fecha = factura.fecha)
        facturaDao.insertFactura(entity)
    }

}