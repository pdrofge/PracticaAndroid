package com.example.practicaandroid.core.screens.Filtros

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.practicaandroid.core.viewmodel.FacturasViewModel
import com.example.practicaandroid.core.viewmodel.FiltrosViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContenidoFiltros(
    filtrosViewModel: FiltrosViewModel,
    facturasViewModel: FacturasViewModel,
    filtrosPrevios: FiltrosFinales,
    navigateBack: () -> Unit
                     ) {

    val filtrosIniciales = remember { EstructuraFiltros(facturasViewModel.facturasIniciales.value) }

    var startDate by remember { mutableStateOf(filtrosPrevios.startDate) }
    var endDate by remember { mutableStateOf(filtrosPrevios.endDate) }
    var sliderPosition by remember {
        mutableStateOf(filtrosPrevios.minAmount.toFloat()..filtrosPrevios.maxAmount.toFloat())
    }
    var isPaid by remember { mutableStateOf(filtrosPrevios.isPaid) }
    var isCancelled by remember { mutableStateOf(filtrosPrevios.isCancelled) }
    var isFixed by remember { mutableStateOf(filtrosPrevios.isFixed) }
    var hasToPay by remember { mutableStateOf(filtrosPrevios.hasToPay) }
    var isPaymentPlan by remember { mutableStateOf(filtrosPrevios.isPaymentPlan) }

    val context = LocalContext.current

    val filtrosDefault = filtrosIniciales  //para resetear filtros, guardamos los valores iniciales

    //para el popup de fecha incorrecta:
    val showDialog = remember { mutableStateOf(false) }


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
                            startDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                colors = buttonColors(
                    containerColor = Color(0x1B000000),
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
                            endDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                colors = buttonColors(
                    containerColor = Color(0x1B000000),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = if (endDate.isEmpty()) "día/mes/año" else endDate)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box( //Línea que separa
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE0E0E0))
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Por un importe", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Valores seleccionados en verde
            Text(
                text = "${sliderPosition.start.toInt()}€ - ${sliderPosition.endInclusive.toInt()}€",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

            // Valores límite estáticos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${filtrosIniciales.minAmount.toInt()}€",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${filtrosIniciales.maxAmount.toInt()}€",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }



            RangeSlider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = filtrosIniciales.minAmount.toFloat()..filtrosIniciales.maxAmount.toFloat(),
                steps = 10,
                colors = SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = Color.LightGray,
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                startThumb = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                },
                endThumb = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }

            )
        }


        Spacer(modifier = Modifier.height(20.dp))
        Box( //Línea que separa
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE0E0E0))
        )
        Spacer(modifier = Modifier.height(20.dp))

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

                if(checkDates(startDate, endDate)) {
                    // Aquí aplicaremos filtros
                    //COMPROBAR que no son valores vacíos
                    val filtrosElegidos = FiltrosFinales(
                        startDate = startDate,
                        endDate = endDate,
                        minAmount = sliderPosition.start.toDouble(),
                        maxAmount = sliderPosition.endInclusive.toDouble(),
                        isPaid = isPaid,
                        isCancelled = isCancelled,
                        isFixed = isFixed,
                        hasToPay = hasToPay,
                        isPaymentPlan = isPaymentPlan

                    )
                    filtrosViewModel.enviarFiltros(filtrosElegidos)
                    navigateBack()
                }else{
                    showDialog.value = true
                }


            },
            colors = buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Aplicar")
        }

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedButton(
            //FILTRAR con contenido inicial
            onClick = {
                startDate = ""
                endDate = ""
                sliderPosition = filtrosIniciales.minAmount.toFloat()..filtrosIniciales.maxAmount.toFloat()//nunca será nulo o vacío
                isPaid = false
                isCancelled = false
                isFixed = false
                hasToPay = false
                isPaymentPlan = false


                var resetFiltros = FiltrosFinales(
                    startDate = filtrosDefault.startDate,
                    endDate = filtrosDefault.endDate,
                    minAmount = sliderPosition.start.toDouble(),
                    maxAmount = sliderPosition.endInclusive.toDouble(),
                    isPaid = filtrosDefault.isPaid,
                    isCancelled = filtrosDefault.isCancelled,
                    isFixed = filtrosDefault.isFixed,
                    hasToPay = filtrosDefault.hasToPay,
                    isPaymentPlan = filtrosDefault.isPaymentPlan
                )


                    filtrosViewModel.enviarFiltros(resetFiltros)


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


        if(showDialog.value){
            PopupFecha(onDismiss = {showDialog.value = false})
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
            .clickable { onCheckedChange(!checked) }
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

@RequiresApi(Build.VERSION_CODES.O)
fun checkDates(startDate: String, endDate: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val today = LocalDate.now()

    val startEmpty = startDate.isEmpty()
    val endEmpty = endDate.isEmpty()

    val start = if (!startEmpty) LocalDate.parse(startDate, formatter) else null
    val end = if (!endEmpty) LocalDate.parse(endDate, formatter) else null

    return when {
        startEmpty && endEmpty -> true
        startEmpty && end != null && (end.isBefore(today) || end.isEqual(today)) -> true
        start != null && (start.isBefore(today) || start.isEqual(today)) && endEmpty -> true
        start != null && end != null &&
                start.isBefore(today) &&
                start.isBefore(end) &&
                (end.isBefore(today) || end.isEqual(today)) -> true
        else -> false
    }
}