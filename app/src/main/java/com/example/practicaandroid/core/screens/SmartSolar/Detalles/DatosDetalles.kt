package com.example.practicaandroid.core.screens.SmartSolar.Detalles

data class DatosDetalles(val CAU : String, val solicitud : String, val tipo:String, val compensacion:String, val potencia:String){

    companion object {

        val ejemplo = DatosDetalles("CAU",
            "No hemos recibido ninguna solicitud de autoconsumo",
            "Con excedentes y compensación Individual - Consumo",
            "compensacion",
            "potencia")

    }


}
