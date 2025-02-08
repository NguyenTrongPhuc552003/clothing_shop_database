package com.example.clothingshopdatabase.database.cart

import com.example.clothingshopdatabase.checkout.options.CartItem

class CartRepository(private val db: CartDatabase) {
    suspend fun addToCart(item: CartItem) {
        val existingItem = db.cartDao().getCartItem(item.productId)
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + item.quantity)
            db.cartDao().updateCartItem(updatedItem)
        } else {
            db.cartDao().addToCart(item)
        }
    }

    suspend fun getCartItems(): List<CartItem> = db.cartDao().getCartItems()

    suspend fun removeFromCart(productId: Int) = db.cartDao().removeFromCart(productId)

    suspend fun clearCart() = db.cartDao().clearCart()
}
