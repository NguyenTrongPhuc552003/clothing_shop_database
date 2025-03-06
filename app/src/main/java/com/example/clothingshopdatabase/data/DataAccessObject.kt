package com.example.clothingshopdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataAccessObject {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(items: List<CartItem>)
    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItem>?>

    @Query("SELECT * FROM cart_items WHERE id = :productId LIMIT 1")
    fun getCartItemById(productId: Int?): Flow<CartItem?>

    @Query("SELECT * FROM cart_items WHERE category = :category")
    fun getCartItemsByCategory(category: String): Flow<List<CartItem>?>

}