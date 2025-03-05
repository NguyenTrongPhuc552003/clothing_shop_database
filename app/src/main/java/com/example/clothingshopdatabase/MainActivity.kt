package com.example.clothingshopdatabase

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.clothingshopdatabase.data.CartDatabase
import com.example.clothingshopdatabase.data.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val db = CartDatabase.getDatabase(applicationContext).cartDao()
            val jsonString =
                readJsonFromAssets(applicationContext, "updated_clothings_database.json")
            val clothes = parseJson(jsonString)
            db.addToCart(clothes)
        }
    }

    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun parseJson(json: String): List<CartItem> {
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("sheet")
        val itemList = mutableListOf<CartItem>()

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            itemList.add(
                CartItem(
                    name = item.getString("name"),
                    price = item.getInt("price"),
                    description = item.getString("description"),
                    image = item.getString("image"),
                    category = item.getString("category")
                )
            )
        }
        return itemList
    }

}