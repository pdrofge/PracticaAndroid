package com.example.practicaandroid.data_retrofit


import android.content.Context
import androidx.room.Room
import com.example.practicaandroid.data_room.FacturaDatabase
import com.example.practicaandroid.data_room.FacturaDBRepository

interface AppContainer{
    val facturaRepository: FacturaDBRepository

}


class DefaultContainer(context: Context): AppContainer{
    private val database: FacturaDatabase by lazy {
        Room.databaseBuilder(
            context,
            FacturaDatabase::class.java,
            "facturas_db"
        ).build()
    }

    override val facturaRepository: FacturaDBRepository by lazy {
        FacturaDBRepository(database.dao)
    }
}