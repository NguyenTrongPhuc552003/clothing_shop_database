package com.example.clothingshopdatabase.model

import java.text.NumberFormat
import java.util.Locale

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val price: Int,
    val stock: Int = 1,
    val totalPrice: Int = price * stock,
    val size: String = ""
) {
    fun formatPrice(): String {
        return NumberFormat.getNumberInstance(Locale("vi", "VN")).format(price)
    }
    fun formatTotalPrice(): String {
        return NumberFormat.getNumberInstance(Locale("vi", "VN")).format(totalPrice)
    }
}


