package com.example.practicaandroid.core.screens.Filtros

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Calendar
import androidx.compose.runtime.key


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    //para recordar ultima fecha seleccionada
    val selectedDateMillis = remember { mutableStateOf<Long?>(null) }


    Column {
        Button(
            onClick = { showDialog.value = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x273F3F3F),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = if (selectedDate.isEmpty()) "día/mes/año" else selectedDate)
        }

        if (showDialog.value) {

            key(selectedDateMillis.value) {
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateMillis.value)

                DatePickerDialog(
                    onDismissRequest = { showDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    selectedDateMillis.value = millis
                                    val calendar = Calendar.getInstance().apply { timeInMillis = millis }
                                    val formattedDate = String.format(
                                        "%02d/%02d/%04d",
                                        calendar.get(Calendar.DAY_OF_MONTH),
                                        calendar.get(Calendar.MONTH) + 1,
                                        calendar.get(Calendar.YEAR)
                                    )
                                    onDateSelected(formattedDate)
                                }
                                showDialog.value = false
                            }
                        ) {
                            Text("Aceptar", color = Color.White)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog.value = false }) {
                            Text("Cancelar", color = Color.White)
                        }
                    },
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    DatePicker(
                        state = datePickerState,
                        colors = DatePickerDefaults.colors(
                            containerColor = Color.White,
                            titleContentColor = Color.Black,
                            headlineContentColor = Color.Black,
                            weekdayContentColor = Color.Black,
                            subheadContentColor = Color.Black,
                            selectedDayContentColor = Color.Black,
                            selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                            dayContentColor = Color.Black,
                            todayContentColor = Color.Gray,
                            todayDateBorderColor = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

