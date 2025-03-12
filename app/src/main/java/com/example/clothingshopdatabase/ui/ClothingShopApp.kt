package com.example.clothingshopdatabase.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothingshopdatabase.data.DataAccessObject
import com.example.clothingshopdatabase.model.ClothingShopScreen
import com.example.clothingshopdatabase.model.Product
import com.example.clothingshopdatabase.ui.screens.CartScreen
import com.example.clothingshopdatabase.ui.screens.InformationScreen
import com.example.clothingshopdatabase.ui.screens.ProductEntryScreen
import com.example.clothingshopdatabase.ui.screens.ProductEntryViewModel
import com.example.clothingshopdatabase.ui.screens.ProductEntryViewModelFactory
import com.example.clothingshopdatabase.ui.screens.ProductScreen
import com.example.clothingshopdatabase.ui.screens.ShowProductsScreen
import com.example.clothingshopdatabase.ui.screens.WelcomeScreen

@Composable
fun ClothingShopApp(
    dao: DataAccessObject,
    navHostController: NavHostController
) {
    val viewModel: ClothingShopViewModel =
        viewModel(factory = ClothingShopAppViewModelFactory(dao))
    val uiState by viewModel.uiState.collectAsState()
    val products by viewModel.getProducts().collectAsState(initial = emptyList())
    NavHost(
        navController = navHostController,
        startDestination = ClothingShopScreen.Home.name
    ) {
        composable(route = ClothingShopScreen.Home.name) {
            WelcomeScreen(
                products = products,
                onCartClick = { navHostController.navigate(ClothingShopScreen.Cart.name) },
                onAddProductClick = {
                    navHostController.navigate(ClothingShopScreen.Entry.name)
                },
                onItemClick = { productId: Int ->
                    navHostController.navigate(
                        ClothingShopScreen.Product.name + "/$productId"
                    )
                },
                onInformationClick = {
                    navHostController.navigate(ClothingShopScreen.Information.name)
                },
                onTrousersClick = { navHostController.navigate(ClothingShopScreen.Show.name + "/trousers") },
                onShirtClick = { navHostController.navigate(ClothingShopScreen.Show.name + "/shirt") },
                onAllClick = { navHostController.navigate(ClothingShopScreen.Show.name + "/all") }
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
            val product by viewModel.findProductById(productId?.toInt())
                .collectAsState(
                    initial =
                    Product(-1, ".", "", "", "", 0)
                )
            ProductScreen(
                product = product,
                onAddToCartClick = {
                    if (it != null)
                        viewModel.addToCart(
                            product.copy(
                                size = it.size
                            )
                        )
                    navHostController.navigateUp()
                },
                onBackClick = { navHostController.navigateUp() },
                onBuyNowClick = { navHostController.navigate(ClothingShopScreen.Cart.name) },
                onUpdateClick = {
                    navHostController.navigate(ClothingShopScreen.Update.name + "/$productId")
                },
                onDeleteClick = {
                    viewModel.removeCartItem(product)
                }
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
                onOrderClick = {
                    viewModel.removeProductsFromCart()
                },
                onBackClick = { navHostController.navigateUp() },
                onAddItemClick = { product ->
                    viewModel.addStock(product)
                },
                onRemoveItemClick = { product ->
                    viewModel.removeStock(product)
                }
            )
        }
        composable(route = ClothingShopScreen.Show.name + "/{category}") {
            val category = it.arguments?.getString("category")
            if (category != null)
                if (category != "all")
                    ShowProductsScreen(
                        onItemClick = { productId: Int ->
                            navHostController.navigate(
                                ClothingShopScreen.Product.name + "/$productId"
                            )
                        },
                        onBackClick = { navHostController.navigateUp() },
                        products = getProductsByCategory(products, category)
                    )
                else
                    ShowProductsScreen(
                        onItemClick = { productId: Int ->
                            navHostController.navigate(
                                ClothingShopScreen.Product.name + "/$productId"
                            )
                        },
                        onBackClick = { navHostController.navigateUp() },
                        products = products
                    )
        }
        composable(route = ClothingShopScreen.Entry.name) {
            val entryViewModel: ProductEntryViewModel =
                viewModel(factory = ProductEntryViewModelFactory(dao))
            val entryUiState by entryViewModel.uiState.collectAsState()
            val product = entryUiState.product
            ProductEntryScreen(
                product = product,
                onUpdateClick = {
                    entryViewModel.updateProduct(it)
                },
                onSaveClick = {
                    entryViewModel.addProduct(product)
                },
                onBackClick = {
                    navHostController.navigateUp()
                },
                isValid = entryViewModel.validProduct()
            )
        }
        composable(route = ClothingShopScreen.Update.name + "/{productId}") {
            val productId = it.arguments?.getString("productId")
            val product by viewModel.findProductById(productId?.toInt())
                .collectAsState(
                    initial =
                    Product(-1, ".", "", "", "", 0)
                )
            val entryViewModel: ProductEntryViewModel =
                viewModel(factory = ProductEntryViewModelFactory(dao))
            val entryUiState by entryViewModel.uiState.collectAsState()
            LaunchedEffect(product) {
                entryViewModel.updateProduct(product)
            }

            ProductEntryScreen(
                product = entryUiState.product,
                onUpdateClick = {
                    entryViewModel.updateProduct(it)
                },
                onSaveClick = {
                    entryViewModel.updateCartItem(entryUiState.product)
                },
                onBackClick = {
                    navHostController.navigateUp()
                },
                isEdit = true,
                isValid = entryViewModel.validProduct()
            )
        }
    }

}

fun getProductsByCategory(
    products: List<Product>,
    category: String
) =
    products.filter {
        it.category == category
    }


