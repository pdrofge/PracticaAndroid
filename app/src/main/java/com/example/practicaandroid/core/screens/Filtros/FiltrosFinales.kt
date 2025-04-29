package com.example.practicaandroid.core.screens.Filtros

data class FiltrosFinales(
    val startDate: String = "",
    val endDate: String = "",
    val minAmount: Double = 0.0,
    val maxAmount: Double = 0.0,
    val isPaid: Boolean = false,
    val isCancelled: Boolean = false,
    val isFixed: Boolean = false,
    val hasToPay: Boolean = false,
    val isPaymentPlan: Boolean = false
)
