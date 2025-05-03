package com.example.practicaandroid.core.screens.ContenidoFacturas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaandroid.domain.model.Factura
import androidx.compose.foundation.lazy.items
import com.example.practicaandroid.core.screens.SmartSolar.Detalles.Popup

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




