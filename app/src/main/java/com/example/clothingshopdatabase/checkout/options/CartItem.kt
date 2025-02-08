package com.example.clothingshopdatabase.checkout.options

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clothingshopdatabase.database.cart.CartDatabase
import com.example.clothingshopdatabase.database.cart.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "cart_items")
data class CartItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int = 0,
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val size: String = "",
    val quantity: Int = 0
)

fun addToCart(context: Context, productId: Int, name: String, price: Double, imageUrl: String) {
    val db = CartDatabase.getDatabase(context)
    val repository = CartRepository(db)

    CoroutineScope(Dispatchers.IO).launch {
        repository.addToCart(CartItem(productId = productId, name = name, price = price, imageUrl = imageUrl, quantity = 1))
    }
}
