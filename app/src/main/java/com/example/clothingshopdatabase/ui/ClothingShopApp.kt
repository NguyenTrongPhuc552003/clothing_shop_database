package com.example.clothingshopdatabase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothingshopdatabase.ui.screens.InformationScreen
import com.example.clothingshopdatabase.ui.screens.ProductScreen
import com.example.clothingshopdatabase.ui.screens.WelcomeScreen

@Composable
fun ClothingShopApp(
    viewModel: ClothingShopViewModel = viewModel(),
    navHostController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = ClothingShopScreen.Home.name
    ) {
        composable(route = ClothingShopScreen.Home.name) {
            WelcomeScreen(
                products = uiState.products,
                onCartClick = {},
                onHomeClick = {},
                onInformationClick = {
                    navHostController.navigate(ClothingShopScreen.Information.name)
                }
            )
        }
        composable(route = ClothingShopScreen.Information.name) {
            InformationScreen(
                onBackClick = {
                    navHostController.navigateUp()
                })
        }
//        composable(route = ClothingShopScreen.Product.name) {
//            ProductScreen(
//
//            )
//        }

    }
}
