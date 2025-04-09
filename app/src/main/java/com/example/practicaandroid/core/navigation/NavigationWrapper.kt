package com.example.practicaandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practicaandroid.core.screens.HomeScreen


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            HomeScreen()
        }
    }
}