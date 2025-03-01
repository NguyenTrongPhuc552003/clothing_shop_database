package com.example.clothingshopdatabase.ui

import androidx.lifecycle.ViewModel
import com.example.clothingshopdatabase.data.DataResource
import com.example.clothingshopdatabase.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ClothingShopViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DataResource)
    val uiState: StateFlow<DataResource> = _uiState.asStateFlow()

    fun getProducts(): List<Product> = _uiState.value.products

}