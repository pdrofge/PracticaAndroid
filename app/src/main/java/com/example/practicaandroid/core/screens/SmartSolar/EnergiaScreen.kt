package com.example.practicaandroid.core.screens.SmartSolar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun EnergiaScreen(){
    Column(
        modifier = Modifier
            //.fillMaxSize()  //en este caso no afecta
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally  //para desplazar al centro el texto
    ) {




        Spacer(modifier = Modifier.height(130.dp))

        Image(
            painter = painterResource(id = R.drawable.energia_sin_fondo),
            contentDescription = "Imagen de mantenimiento",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                //.align(Alignment.CenterHorizontally)  //en este caso no afecta
        )

        Spacer(modifier = Modifier.height(100.dp))




        Text(
            text = "Estamos trabajando en mejorar la App. Tus paneles solares siguen produciendo, " +
                    "en breve podrás seguir viendo tu producción solar. Sentimos las molestias.",
            color = Color(0xFF4F4F4F),
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(0.75f), // para limitar el ancho del texto
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )









    }
}