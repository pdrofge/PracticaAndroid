package com.example.practicaandroid.data_room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaandroid.domain.model.Factura


@Database(entities = [Factura::class], version = 1)
abstract class FacturaDatabase : RoomDatabase( ) {
    abstract val dao: FacturaDao
}