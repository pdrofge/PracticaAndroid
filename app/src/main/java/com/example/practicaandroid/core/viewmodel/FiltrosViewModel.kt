package com.example.practicaandroid.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practicaandroid.MainApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.practicaandroid.data_retrofit.FacturasResponse
import com.example.practicaandroid.domain.model.Factura


class FiltrosViewModel(
    private val facturasViewModel : FacturasViewModel

) : ViewModel() {



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



