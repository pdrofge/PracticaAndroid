package com.example.practicaandroid.core.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practicaandroid.MainApplication
import com.example.practicaandroid.core.screens.Filtros.Filtrar
import com.example.practicaandroid.core.screens.Filtros.FiltrosFinales
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.practicaandroid.data_retrofit.FacturasResponse
import com.example.practicaandroid.data_retrofit.RetrofitInstance
import com.example.practicaandroid.domain.model.Factura
import kotlinx.coroutines.launch


class FacturasViewModel : ViewModel() {

    private val _facturas = MutableStateFlow<List<Factura>>(emptyList())
    val facturas: StateFlow<List<Factura>> = _facturas
    private val _facturasIniciales = MutableStateFlow<List<Factura>>(emptyList())
    val facturasIniciales: StateFlow<List<Factura>> = _facturasIniciales

    //para recordar filtros ya aplicados:
    private val _filtrosActuales = MutableStateFlow(FiltrosFinales())
    val filtrosActuales: StateFlow<FiltrosFinales> = _filtrosActuales

    init {

        viewModelScope.launch {

            try {
                _facturas.value = RetrofitInstance.repository.getFacturas()
            }catch (e : Exception){
            }
            _facturasIniciales.value = _facturas.value
            // Calculamos min y max al arrancar
            val minAmount = _facturasIniciales.value.minOfOrNull { it.importeOrdenacion } ?: 0.0
            val maxAmount = _facturasIniciales.value.maxOfOrNull { it.importeOrdenacion }?.plus(1) ?: 500.0  //500 de maximo por ahora

            _filtrosActuales.value = FiltrosFinales(
                minAmount = minAmount,
                maxAmount = maxAmount
            )
        }

    }


    fun aplicarFiltros(filtros: FiltrosFinales) {
       _facturas.value = Filtrar(facturasIniciales.value, filtros)
        _filtrosActuales.value = filtros
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



