package com.example.practicaandroid.core.screens.ContenidoFacturas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.practicaandroid.domain.model.Factura
import androidx.compose.foundation.lazy.items

@Composable
fun ContenidoFacturas(facturas: List<Factura>) {


    val showPopup = remember { mutableStateOf(false) }



    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(facturas) { factura ->
            ListaFacturas(factura) {
                showPopup.value = true
            }
        }
    }

    if (showPopup.value) {
        PopupFacturas(onDismiss = {showPopup.value = false})
    }
}




