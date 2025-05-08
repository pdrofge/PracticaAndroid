package com.example.practicaandroid.core.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practicaandroid.MainApplication
import com.example.practicaandroid.core.screens.ContenidoFacturas.FacturasMock
import com.example.practicaandroid.core.screens.Filtros.Filtrar
import com.example.practicaandroid.core.screens.Filtros.FiltrosFinales
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.practicaandroid.data_retrofit.RetrofitInstance
import com.example.practicaandroid.domain.model.Factura
import kotlinx.coroutines.launch


class FacturasViewModel() : ViewModel() {

    private val _facturas = MutableStateFlow<List<Factura>>(emptyList())
    val facturas: StateFlow<List<Factura>> = _facturas
    private val _facturasIniciales = MutableStateFlow<List<Factura>>(emptyList())
    val facturasIniciales: StateFlow<List<Factura>> = _facturasIniciales

    //para recordar filtros ya aplicados:
    private val _filtrosActuales = MutableStateFlow(FiltrosFinales())
    val filtrosActuales: StateFlow<FiltrosFinales> = _filtrosActuales

    //para recordar el estado del boton
    private val _usarRetrofit = MutableStateFlow(true)
    val usarRetrofit: StateFlow<Boolean> = _usarRetrofit

    //para simular una carga mientras cargo facturas usando Retrofit:
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {

        viewModelScope.launch {
            _isLoading.value = true
             try {
                _facturas.value = RetrofitInstance.repository.getFacturas()
            }catch (e : Exception){

            }finally{_isLoading.value = false
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


    fun recargarFacturas(usarRetrofit: Boolean) {
        _usarRetrofit.value = usarRetrofit
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _facturas.value = if (usarRetrofit) {
                    RetrofitInstance.repository.getFacturas()
                } else {
                    FacturasMock.getMock().facturas
                }
            } catch (e: Exception) {
                //Log.e("FacturasViewModel", "Error al obtener facturas", e)
                _facturas.value = emptyList()
            }finally {
                _isLoading.value = false

            }

            _facturasIniciales.value = _facturas.value

            val minAmount = _facturasIniciales.value.minOfOrNull { it.importeOrdenacion } ?: 0.0
            val maxAmount = _facturasIniciales.value.maxOfOrNull { it.importeOrdenacion }?.plus(1) ?: 500.0

            _filtrosActuales.value = FiltrosFinales(
                minAmount = minAmount,
                maxAmount = maxAmount
            )
        }
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



