package com.example.practicaandroid.core.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaandroid.core.screens.Filtros.ContenidoFiltros
import com.example.practicaandroid.core.viewmodel.FacturasViewModel
import com.example.practicaandroid.core.viewmodel.FiltrosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FiltrosScreen(navigateBack: () -> Unit,filtrosViewModel: FiltrosViewModel,facturasViewModel: FacturasViewModel
) {
    val layoutDirection = LocalLayoutDirection.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Cerrar",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // Contenido principal
        Box(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding() - 8.dp, //quitamos algo de espacio entre toolbar y título
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                Text(
                    text = "Filtrar facturas",
                    color = Color.Black,
                    fontSize = 37.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.height(35.dp))

                ContenidoFiltros(filtrosViewModel,facturasViewModel, facturasViewModel.filtrosActuales.collectAsState().value)

            }
        }
    }
}