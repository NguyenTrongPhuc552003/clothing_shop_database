package com.example.clothingshopdatabase.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothingshopdatabase.model.ClothingShopScreen
import com.example.clothingshopdatabase.model.Product
import com.example.clothingshopdatabase.ui.screens.CartScreen
import com.example.clothingshopdatabase.ui.screens.InformationScreen
import com.example.clothingshopdatabase.ui.screens.ProductScreen
import com.example.clothingshopdatabase.ui.screens.WelcomeScreen

@Composable
fun ClothingShopApp(
    viewModel: ClothingShopViewModel = viewModel(),
    navHostController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val products by viewModel.getProducts().collectAsState(initial = emptyList())
    NavHost(
        navController = navHostController,
        startDestination = ClothingShopScreen.Home.name
    ) {
        composable(route = ClothingShopScreen.Home.name) {
            WelcomeScreen(
                products = products,
                onCartClick = {navHostController.navigate(ClothingShopScreen.Cart.name)},
                onHomeClick = {},
                onItemClick = { productId: Int ->
                    Log.d("productID",productId.toString())
                    navHostController.navigate(
                        ClothingShopScreen.Product.name + "/$productId"
                    )
                },
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
        composable(route = ClothingShopScreen.Product.name + "/{productId}") {
            val productId = it.arguments?.getString("productId")
            Log.d("productID2",productId.toString())
            val product by viewModel.findProductById(productId?.toInt())
                .collectAsState(
                    initial =
                    Product(-1, ".", "", "", "", 0)
                )
            ProductScreen(
                product = product,
                onAddToCartClick = {},
                onBackClick = { navHostController.navigateUp() },
                onBuyNowClick = {}
            )
        }
        composable(route = ClothingShopScreen.Cart.name) {
            CartScreen(
                products = uiState.cartList ?: emptyList(),
                subTotal = viewModel.getSubtotal().toString(),
                totalQuantity = viewModel.getTotalQuantity().toString(),
                delivery = DELIVERY.toString(),
                totalPrice = viewModel.getTotal().toString(),
                departurePoint = "Hwang Gyeol",
                destination = "227 Nguyen Trai Street, W4, D5, HCM City",
                onOrderClick = {},
                onBackClick = { navHostController.navigateUp() }
            )
        }
    }
}
