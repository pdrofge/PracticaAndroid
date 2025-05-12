package com.example.practicaandroid.core.screens

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaandroid.R
import com.example.practicaandroid.core.screens.ContenidoFacturas.ContenidoFacturas
import com.example.practicaandroid.core.viewmodel.FacturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturasScreen(
    navigateBack: () -> Unit,
    navigateToFiltros: () -> Unit,
    viewModel: FacturasViewModel
) {


    val layoutDirection = LocalLayoutDirection.current
    val facturas = viewModel.facturas.collectAsState()
    val isRetrofit = viewModel.usarRetrofit.collectAsState()
    //para simular carga al usar retrofit
    val isLoading = viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable(onClick = navigateBack)
                            .padding(start = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Consumo",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    val icon: Painter = painterResource(id = R.drawable.filtrosiconosinfondo)
                    IconButton(onClick = { navigateToFiltros() }) {
                        Image(
                            painter = icon,
                            contentDescription = "Filtrar",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),

            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding() - 8.dp,
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = " Facturas",
                    color = Color.Black,
                    fontSize = 37.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.height(35.dp))
                val context = LocalContext.current

                if (facturas.value.isNotEmpty()) {
                    ContenidoFacturas(facturas.value)


                } else if(!isInternetAvailable(context) && isRetrofit.value) {
                    Text(
                        text = "No hay conexión a internet, solo podrás ver facturas locales",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }else{
                    Text(
                        text = " No hay facturas disponibles",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Indicador de carga
            if (isLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x88000000)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        color = Color(0xFF8BC34A)
                    )
                }
            }


            Button(
                //recargo facturas:
                onClick = { viewModel.recargarFacturas(!isRetrofit.value) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRetrofit.value) MaterialTheme.colorScheme.primary else Color(0xFF0066CC)
                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(width = 150.dp, height = 60.dp),
                enabled = !isLoading.value // Desactivamos botón mientras se carga
            ) {
                Text(
                    text = if (isRetrofit.value) "Retrofit" else "Retromock",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}