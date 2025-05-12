package com.example.practicaandroid.core.screens.ContenidoFacturas

import androidx.compose.runtime.Composable
import com.example.practicaandroid.domain.model.Factura
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.ui.Alignment

@Composable
fun ListaFacturas(factura: Factura, onClick: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            //.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Column(modifier = Modifier.weight(1f)) {


                if (factura.descEstado == "Pagada"){
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Text(
                    text = formatearFecha(factura.fecha),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
                if (factura.descEstado != "Pagada") {
                    Text(
                        text = factura.descEstado,
                        color = Color(0xFFFF6B6B),
                        fontSize = 16.sp
                    )
                }

            }

            Text(
                text = "${redondearImporte(factura.importeOrdenacion)} €",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium

            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Go",
                tint = Color(0x4D0E0E0E)
            )



        }
        if (factura.descEstado == "Pagada"){
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
    HorizontalDivider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(start = 16.dp)
    )
}



fun redondearImporte(importe: Double): String {
    return String.format("%.2f", importe)
}

fun formatearFecha(fecha: String): String {
    val partes = fecha.split("/")
    val mes = when (partes[1].toInt()) {
        1 -> "Ene"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Abr"
        5 -> "May"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Ago"
        9 -> "Sep"
        10 -> "Oct"
        11 -> "Nov"
        12 -> "Dic"
        else -> partes[1]
    }
    return "${partes[0]} $mes ${partes[2]}"
}