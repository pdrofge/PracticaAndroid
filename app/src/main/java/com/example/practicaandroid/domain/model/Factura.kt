package com.example.practicaandroid.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Factura(

    val descEstado: String,
    val importeOrdenacion: Double,
    val fecha: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
){

}
