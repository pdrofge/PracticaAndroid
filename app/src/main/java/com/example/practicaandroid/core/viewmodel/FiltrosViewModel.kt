package com.example.practicaandroid.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicaandroid.core.screens.Filtros.FiltrosFinales



class FiltrosViewModel(
    private val facturasViewModel : FacturasViewModel

) : ViewModel() {

    fun enviarFiltros(filtros : FiltrosFinales){
            facturasViewModel.aplicarFiltros(filtros)
    }



    companion object{
        fun provideFactory(facturasViewModel: FacturasViewModel) : ViewModelProvider.Factory =
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                     return FiltrosViewModel(facturasViewModel) as T
                }
            }
    }

}



