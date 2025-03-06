package com.example.clothingshopdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val image: String = "",
    val category: String = ""
)
