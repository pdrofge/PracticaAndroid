package com.example.practicaandroid.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practicaandroid.MainApplication
import com.example.practicaandroid.core.screens.Filtros.Filtrar
import com.example.practicaandroid.core.screens.Filtros.FiltrosFinales
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



    fun aplicarFiltros(filtros: FiltrosFinales) {
       _facturas.value = Filtrar(FacturasResponse.listaEstatica.facturas, filtros)
    }

    companion object{
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MainApplication)
                val container = app.container
                FacturasViewModel(

                )
            }
        }
    }

}



