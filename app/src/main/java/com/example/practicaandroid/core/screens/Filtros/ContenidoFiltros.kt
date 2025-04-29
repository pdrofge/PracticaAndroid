package com.example.practicaandroid.core.screens.Filtros

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.practicaandroid.domain.model.Factura
import java.util.*

@Composable
fun ContenidoFiltros(listaFacturas: List<Factura>) {

    val filtrosIniciales = remember { EstructuraFiltros(listaFacturas) }

    var startDate by remember { mutableStateOf(filtrosIniciales.startDate) }
    var endDate by remember { mutableStateOf(filtrosIniciales.endDate) }
    var sliderPosition by remember {
        mutableStateOf(filtrosIniciales.minAmount.toFloat()..filtrosIniciales.maxAmount.toFloat())
    }

    var isPaid by remember { mutableStateOf(filtrosIniciales.isPaid) }
    var isCancelled by remember { mutableStateOf(filtrosIniciales.isCancelled) }
    var isFixed by remember { mutableStateOf(filtrosIniciales.isFixed) }
    var hasToPay by remember { mutableStateOf(filtrosIniciales.hasToPay) }
    var isPaymentPlan by remember { mutableStateOf(filtrosIniciales.isPaymentPlan) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(text = "Con fecha de emisión", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                            startDate = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = if (startDate.isEmpty()) "día/mes/año" else startDate)
            }

            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                            endDate = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = if (endDate.isEmpty()) "día/mes/año" else endDate)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Por un importe", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${sliderPosition.start.toInt()} €  -  ${sliderPosition.endInclusive.toInt()} €",
            style = MaterialTheme.typography.bodyMedium
        )

        RangeSlider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = filtrosIniciales.minAmount.toFloat()..filtrosIniciales.maxAmount.toFloat(),
            steps = 10,
            modifier = Modifier.padding(horizontal = 8.dp),
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF4CAF50),
                activeTickColor = Color(0xFF4CAF50),
                thumbColor = Color(0xFF4CAF50)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Por estado", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        CheckboxWithLabel(
            label = "Pagadas",
            checked = isPaid,
            onCheckedChange = { isPaid = it }
        )

        CheckboxWithLabel(
            label = "Anuladas",
            checked = isCancelled,
            onCheckedChange = { isCancelled = it }
        )

        CheckboxWithLabel(
            label = "Cuota Fija",
            checked = isFixed,
            onCheckedChange = { isFixed = it }
        )

        CheckboxWithLabel(
            label = "Pendientes de pago",
            checked = hasToPay,
            onCheckedChange = { hasToPay = it }
        )

        CheckboxWithLabel(
            label = "Plan de pago",
            checked = isPaymentPlan,
            onCheckedChange = { isPaymentPlan = it }
        )

        Spacer(modifier = Modifier.height(24.dp))


        OutlinedButton(
            onClick = {
                // Aquí aplicaremos filtros
            },
            colors = buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF4CAF50)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Aplicar")
        }

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedButton(
            onClick = {
                startDate = ""
                endDate = ""
                sliderPosition = filtrosIniciales.minAmount.toFloat()..filtrosIniciales.maxAmount.toFloat()
                isPaid = false
                isCancelled = false
                isFixed = false
                hasToPay = false
                isPaymentPlan = false
            },
            colors = buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Eliminar filtros")
        }
    }
}

@Composable
fun CheckboxWithLabel(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Black,
                uncheckedColor = Color.Black,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
