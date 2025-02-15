package com.example.clothingshopdatabase.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat
import java.util.Locale

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val price: Int,
    val size: List<Size>
){
    fun formatPrice(): String{
        return NumberFormat.getNumberInstance(Locale("vi","VN")).format(price)
    }
}


