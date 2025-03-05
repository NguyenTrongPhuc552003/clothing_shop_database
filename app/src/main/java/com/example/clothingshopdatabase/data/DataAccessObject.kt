package com.example.clothingshopdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataAccessObject {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(items: List<CartItem>)
    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartItem>?

    @Query("SELECT * FROM cart_items WHERE id = :productId LIMIT 1")
    suspend fun getCartItemById(productId: Int): CartItem?

    @Query("SELECT * FROM cart_items WHERE category = :category")
    suspend fun getCartItemsByCategory(category: String): List<CartItem>?

}