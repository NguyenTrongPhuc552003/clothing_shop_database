package com.example.clothingshopdatabase.data

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "cart_items")
data class CartItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val image: String = "",
    val category: String = ""
)
