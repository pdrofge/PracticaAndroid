package com.example.practicaandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.practicaandroid.core.screens.FacturasScreen
import com.example.practicaandroid.core.screens.FiltrosScreen
import com.example.practicaandroid.core.screens.HomeScreen
import com.example.practicaandroid.core.screens.SmartSolarScreen
import com.example.practicaandroid.core.viewmodel.FacturasViewModel
import com.example.practicaandroid.core.viewmodel.FiltrosViewModel



@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val facturasViewModel: FacturasViewModel = viewModel()
    val filtrosViewModel: FiltrosViewModel = viewModel()
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            HomeScreen(
                navigateToSS = { navController.navigate(SmartSolar.route) },
                navigateToF = { navController.navigate(Facturas.route) }
            )


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


        composable(Facturas.route){

            FacturasScreen(


                navigateBack = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route){inclusive = true}
                    }
                },

                navigateToFiltros = {
                    navController.navigate(Filtros.route)
                },
                viewModel = facturasViewModel


            )
        }


        composable(Filtros.route){

            FiltrosScreen(
                navigateBack = {
                    navController.navigate(Facturas.route) {
                        popUpTo(Facturas.route){inclusive = true}
                    }
                },
                filtrosViewModel = filtrosViewModel,
                facturasViewModel = facturasViewModel
            )

        }



    }
}