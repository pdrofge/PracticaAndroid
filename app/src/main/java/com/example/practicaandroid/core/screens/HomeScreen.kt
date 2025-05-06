package com.example.practicaandroid.core.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun HomeScreen(navigateToSS: () -> Unit, navigateToF: () -> Unit){
    //dos destinos: facturas y smart solar
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA8E6A1))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Image(  //título imagen Iberdrola
                painter = painterResource(id = R.drawable.iberdrola),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
            )

            // Subtítulo
            Text(
                //POSIBLE AÑADIDO: más adelante preguntar trato
                text = "Nos alegra verte de nuevo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            // Botones
            Button(
                onClick = { navigateToF()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCC80)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Facturas", fontSize = 18.sp)
            }

            Button(
                onClick = {navigateToSS()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCC80)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Smart Solar", fontSize = 18.sp)
            }
        }
    }
}