package com.example.clothingshopdatabase.database.cart

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clothingshopdatabase.checkout.options.CartItem

interface DataAccessObject {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartItem)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartItem>

    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getCartItem(productId: Int): CartItem?

    @Update
    suspend fun updateCartItem(item: CartItem)

    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun removeFromCart(productId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}