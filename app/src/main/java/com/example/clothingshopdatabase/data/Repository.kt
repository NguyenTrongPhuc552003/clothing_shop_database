package com.example.clothingshopdatabase.data

class CartRepository(private val dao: DataAccessObject) {
    suspend fun addToCart(cartItems: List<CartItem>) = dao.addToCart(cartItems)

    suspend fun getCartItems(): List<CartItem>? = dao.getCartItems()

    suspend fun getCartItemsByCategory(category: String): List<CartItem>? = dao.getCartItemsByCategory(category)


}
