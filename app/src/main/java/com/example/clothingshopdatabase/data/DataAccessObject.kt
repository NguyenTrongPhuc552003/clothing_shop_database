package com.example.clothingshopdatabase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCartItem(item: CartItem)

    @Update
    suspend fun updateCartItem(item: CartItem)

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteCartItem(id: Int)
}