package com.example.practicaandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.practicaandroid.core.screens.HomeScreen
import com.example.practicaandroid.core.screens.SmartSolarScreen


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            HomeScreen{navController.navigate(SmartSolar.route)}
        }

        composable(SmartSolar.route){

            SmartSolarScreen(

                navigateBack = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route){inclusive = true}
                    }
                }


            )
        }

    }
}