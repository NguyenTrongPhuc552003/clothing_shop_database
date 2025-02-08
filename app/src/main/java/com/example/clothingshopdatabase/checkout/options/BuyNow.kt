package com.example.clothingshopdatabase.checkout.options

import android.content.Context
import com.example.clothingshopdatabase.database.cart.CartDatabase
import com.example.clothingshopdatabase.database.cart.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun buyNow(context: Context, product: CartItem) {
    val db = CartDatabase.getDatabase(context)
    val repository = CartRepository(db)

    CoroutineScope(Dispatchers.IO).launch {
        repository.clearCart() // Clear the cart after checkout
    }

    // Redirect to checkout page (to be implemented in UI)
}
