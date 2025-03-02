package com.example.clothingshopdatabase.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat
import java.util.Locale

class Product(
    val id: Int = 0,
    val name: String = "",
    val category: String = "", // coats, pants, or all products
    val price: Int = 0,
    val image: String,
    val description: String = "",
    val stock: Int = 0,
    val size: String = "" // S, M, L, XL, or all sizes
){
    fun formatPrice(): String{
        return NumberFormat.getNumberInstance(Locale("vi","VN")).format(price)
    }
}