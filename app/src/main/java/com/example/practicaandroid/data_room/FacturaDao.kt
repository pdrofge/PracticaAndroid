package com.example.practicaandroid.data_room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaandroid.domain.model.Factura

@Dao
interface FacturaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFactura(factura: Factura)



    @Query("SELECT * FROM Factura")
    suspend fun getFacturas() : List<Factura>


    @Query("DELETE FROM Factura")
    suspend fun deleteAllFacturas()

}