package com.example.practicaandroid.core.screens.ContenidoFacturas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun PopupFacturas(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) //para evitar márgenes a ambos lados del popup
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x07000000))
                .clickable(onClick = onDismiss), // para salir del popoup
            contentAlignment = Alignment.Center
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f) //ancho
                    .height(285.dp) //largo
                    .offset(y = (-80).dp), //altura
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Información",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "Esta funcionalidad aún no está disponible",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8BC34A)
                        )
                    ) {
                        Text(
                            text = "Cerrar",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }



    }
}