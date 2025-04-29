package com.example.practicaandroid

import android.app.Application
import com.example.practicaandroid.data_retrofit.AppContainer
import com.example.practicaandroid.data_retrofit.DefaultContainer


class MainApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(applicationContext)
    }
}