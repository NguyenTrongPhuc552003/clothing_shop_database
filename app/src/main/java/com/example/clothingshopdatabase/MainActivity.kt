package com.example.clothingshopdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.clothingshopdatabase.checkout.options.addToCart
import com.example.clothingshopdatabase.database.cart.CartDatabase
import com.example.clothingshopdatabase.database.cart.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this

        // Read and parse the JSON file
        val inputStream: InputStream = assets.open("clothings-database.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val productsArray = jsonObject.getJSONArray("sheet")

        // Test backend functions for each product
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until productsArray.length()) {
                val product = productsArray.getJSONObject(i)
                val name = product.getString("name")
                val price = product.getString("price").replace(".", "").replace(" VND", "").toDouble()
                val imageUrl = product.getString("image")

                addToCart(context, i + 1, name, price, imageUrl)
                println("Product added to cart: $name")

                // Test getCartItems function
                val db = CartDatabase.getDatabase(context)
                val repository = CartRepository(db)
                val cartItems = repository.getCartItems()
                println("Cart Items: $cartItems")

                // Test getOrderSummary function
                val orderSummary = repository.getOrderSummary()
                println("Order Summary: $orderSummary")
            }
        }
    }
}