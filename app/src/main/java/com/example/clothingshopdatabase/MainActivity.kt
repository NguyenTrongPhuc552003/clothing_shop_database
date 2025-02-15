package com.example.clothingshopdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.clothingshopdatabase.data.DataResource
import com.example.clothingshopdatabase.ui.screen.welcome.WelcomeScreen
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightNavigationBars = false
            isAppearanceLightStatusBars = false
        }
        setContent {
            ClothingShopDatabaseTheme(darkTheme = false) {
                WelcomeScreen(products = DataResource.products)
            }
        }
    }
}

