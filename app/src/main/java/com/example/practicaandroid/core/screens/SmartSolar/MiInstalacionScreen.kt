package com.example.practicaandroid.core.screens.SmartSolar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaandroid.R

@Composable
fun MiInstalacionScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Texto principal
        Text(
            text = "Aquí tienes los datos de tu instalación fotovoltaica en tiempo real",
            color = Color.Black,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Texto de Autoconsumo
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "Autoconsumo: ",
                color = Color.Black.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
            Text(
                text = "92%",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Imagen del gráfico
        Image(
            painter = painterResource(id = R.drawable.grafico1_sin_fondo),
            contentDescription = "Gráfico de instalación solar",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp) // Ajusta esto según el tamaño deseado
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f)) // Empuja el contenido hacia arriba dejando espacio inferior
    }

}