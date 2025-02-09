package com.example.clothingshopdatabase.checkout.options

import android.content.Context
import com.example.clothingshopdatabase.database.cart.CartDatabase
import com.example.clothingshopdatabase.database.cart.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun buyNow(context: Context, product: CartItem, onCheckoutReady: (List<List<String>>, List<List<String>>) -> Unit) {
    val db = CartDatabase.getDatabase(context)
    val repository = CartRepository(db)

    CoroutineScope(Dispatchers.IO).launch {
        repository.addToCart(product) // Add the selected product to the cart

        val itemsTable = repository.getCartItems().map {
            listOf(it.name, "x${it.quantity}", "${it.price * it.quantity} VND")
        }
        val summaryTable = repository.getOrderSummary()

        // Callback to UI for navigation (to be implemented)
        onCheckoutReady(itemsTable, summaryTable)
    }
}
