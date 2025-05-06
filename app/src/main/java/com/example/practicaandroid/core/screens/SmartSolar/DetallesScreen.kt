package com.example.practicaandroid.core.screens.SmartSolar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaandroid.R
import com.example.practicaandroid.core.screens.SmartSolar.Detalles.DatosDetalles
import com.example.practicaandroid.core.screens.SmartSolar.Detalles.DatosDetallesMock
import com.example.practicaandroid.core.screens.SmartSolar.Detalles.Popup

@Composable
fun DetallesScreen(datos: DatosDetalles){

    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            LabeledField(
                label = "CAU (Código Autoconsumo)",
                value = datos.CAU
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Usamos Row para colocar el icono junto al texto
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    LabeledField(
                        label = "Estado solicitud alta autoconsumidor",
                        value = datos.solicitud
                    )
                }
                IconButton(
                    onClick = { showDialog.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Información",
                        tint = Color(0xFF007AFF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            LabeledField(
                label = "Tipo autoconsumo",
                value = datos.tipo
            )

            Spacer(modifier = Modifier.height(16.dp))


            LabeledField(
                label = "Compensación de excedentes",
                value = datos.compensacion
            )

            Spacer(modifier = Modifier.height(16.dp))


            LabeledField(
                label = "Potencia de instalación",
                value = datos.potencia
            )
        }
    }


    //Llamamos al popup
    if(showDialog.value){
        Popup(onDismiss = {showDialog.value = false})
    }

}

@Composable
fun LabeledField(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))


        Box( //Subrayamos cada campo de texto
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE0E0E0))
        )
    }
}

