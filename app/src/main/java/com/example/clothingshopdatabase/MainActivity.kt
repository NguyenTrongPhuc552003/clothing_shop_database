package com.example.clothingshopdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.clothingshopdatabase.data.CartDatabase
import com.example.clothingshopdatabase.ui.ClothingShopApp
import com.example.clothingshopdatabase.ui.ClothingShopAppViewModelFactory
import com.example.clothingshopdatabase.ui.ClothingShopViewModel
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val dao = remember { CartDatabase.getDatabase(context).cartDao() }
            val navController = rememberNavController()
//            viewModel.deleteItems()
//            viewModel.loadDataFromJson(this)
            ClothingShopDatabaseTheme(darkTheme = false) {
                ClothingShopApp(
                    dao = dao,
                    navHostController = navController
                )
            }
        }
    }
}