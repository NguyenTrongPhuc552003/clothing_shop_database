package com.example.clothingshopdatabase.ui

import androidx.lifecycle.ViewModel
import com.example.clothingshopdatabase.clothing.management.Repository
import com.example.clothingshopdatabase.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ClothingShopViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Repository)
    val uiState: StateFlow<Repository> = _uiState.asStateFlow()

    fun getProducts(
        category: String,
        basicInfo: Boolean
    ): List<Product> = _uiState.value.getProductsByCategory(category,basicInfo)

}