package com.example.clothingshopdatabase.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clothingshopdatabase.data.CartItem
import com.example.clothingshopdatabase.data.DataAccessObject
import com.example.clothingshopdatabase.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject

const val DELIVERY = 30000

data class ClothingShopViewModelData(
    val cartList: List<Product>? = emptyList()
)

class ClothingShopAppViewModelFactory(private val dao: DataAccessObject) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClothingShopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClothingShopViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class ClothingShopViewModel(private val dao: DataAccessObject) : ViewModel() {
    private val _uiState = MutableStateFlow(ClothingShopViewModelData())
    val uiState = _uiState.asStateFlow()

    fun getProducts() =
        dao.getCartItems()
            .map { cartItems ->
                cartItems?.map { cartItem ->
                    Product(
                        id = cartItem.id,
                        name = cartItem.name,
                        category = cartItem.category,
                        description = cartItem.description,
                        image = cartItem.image,
                        price = cartItem.price
                    )
                } ?: emptyList()
            }

    fun addToCart(product: Product) {
        val checkExist = _uiState.value.cartList?.find {
            it.id == product.id && it.size == product.size
        }
        Log.d("checkExist",checkExist.toString())
        val updateCart = _uiState.value.cartList?.toMutableList()
        Log.d("updateCart",updateCart.toString())
        if (checkExist != null) {
            updateCart?.set(
                updateCart.indexOf(checkExist),
                checkExist.copy(stock = checkExist.stock + 1)
            )
        } else
            updateCart?.add(product)
        Log.d("updateCart",updateCart.toString())
        _uiState.value = _uiState.value.copy(cartList = updateCart)
        Log.d("Size",_uiState.value.cartList.toString())
    }

    fun findProductByCategory(category: String) =
        getProducts().map {

        }

    fun findProductById(productId: Int?) = dao.getCartItemById(productId).map { cartItem ->
        cartItem?.let {
            Product(
                id = cartItem.id,
                name = cartItem.name,
                category = cartItem.category,
                description = cartItem.description,
                image = cartItem.image,
                price = cartItem.price
            )
        } ?: Product(-1,".","","","",0)
    }

    fun getSubtotal(): Int =
        _uiState.value.cartList?.sumOf { it.totalPrice } ?: 0
    fun getTotalQuantity(): Int =
        _uiState.value.cartList?.sumOf { it.stock } ?: 0
    fun getTotal(): Int =
        getSubtotal() + DELIVERY

    fun readJsonFromAssets(context: Context, fileName: String): String {
        Log.d("filename",fileName)
        val result = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Log.d("readJsonResult",result)
        return result
    }

    fun parseJson(json: String): List<CartItem> {
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("sheet")
        val itemList = mutableListOf<CartItem>()

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            itemList.add(
                CartItem(
                    name = item.getString("name"),
                    price = item.getInt("price"),
                    description = item.getString("description"),
                    image = item.getString("image"),
                    category = item.getString("category")
                )
            )
        }
        return itemList
    }
    fun loadDataFromJson(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val jsonString = readJsonFromAssets(context, "updated_clothings_database.json")
            val clothes = parseJson(jsonString)
            Log.d("test",jsonString)
            dao.addToCart(clothes)
        }
    }}