package com.example.practicaandroid.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.practicaandroid.data_retrofit.FacturasResponse
import com.example.practicaandroid.domain.model.Factura


class FacturasViewModel : ViewModel() {
    private val _facturas = MutableStateFlow<List<Factura>>(emptyList())
    val facturas: StateFlow<List<Factura>> = _facturas

    init {
        // De momento cargamos la lista estática
        _facturas.value = FacturasResponse.listaEstatica.facturas
    }
}