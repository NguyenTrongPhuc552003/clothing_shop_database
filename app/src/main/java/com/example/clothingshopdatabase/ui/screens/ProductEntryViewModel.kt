package com.example.clothingshopdatabase.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clothingshopdatabase.data.CartItem
import com.example.clothingshopdatabase.data.DataAccessObject
import com.example.clothingshopdatabase.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProductEntryViewModelData(
    val product: Product = Product()
)

class ProductEntryViewModelFactory(private val dao: DataAccessObject) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductEntryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ProductEntryViewModel(private val dao: DataAccessObject) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductEntryViewModelData())
    val uiState = _uiState.asStateFlow()
    fun addProduct(product: Product) {
        viewModelScope.launch {
            dao.addCartItem(
                CartItem(
                    name = product.name,
                    price = product.price,
                    description = product.description,
                    image = product.image,
                    category = product.category
                )
            )
        }
    }

    fun updateProduct(product: Product) {
        _uiState.value = _uiState.value.copy(product = product)
    }

    fun updateCartItem(product: Product){
        viewModelScope.launch {
            dao.updateCartItem(
                CartItem(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    description = product.description,
                    image = product.image,
                    category = product.category
                )
            )
        }
    }

    fun validProduct(): Boolean =
        _uiState.value.product.name != "" &&
                _uiState.value.product.category != "" &&
                _uiState.value.product.description != "" &&
                _uiState.value.product.price > 0

}