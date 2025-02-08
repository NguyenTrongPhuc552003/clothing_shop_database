package com.example.clothingshopdatabase.clothing.management

object Repository {
    /*
    * A list of products.
    * Each product has an id, name, category, price, imageURL, description, stock, and size.
    * - id: The unique identifier of the product.
    * - name: The name of the product.
    * - category: The category of the product (coats, pants, or all).
    * - price: The price of the product.
    * - imageURL: The URL of the product image.
    * - description: The description of the product.
    * - stock: The number of items in stock.
    * - size: The size of the product (S, M, L, XL, or all).
    * */
    private val modelsLists = listOf (
        Product(1, "T-shirt", "coats", 20.0, "https://www.example.com/tshirt.jpg", "A simple t-shirt", 10, "S"),
        Product(2, "Jeans", "pants", 40.0, "https://www.example.com/jeans.jpg", "A pair of jeans", 5, "M"),
        Product(3, "Sweater", "coats", 30.0, "https://www.example.com/sweater.jpg", "A cozy sweater", 7, "L"),
        Product(4, "Shorts", "pants", 25.0, "https://www.example.com/shorts.jpg", "A pair of shorts", 3, "XL"),
        Product(5, "Dress", "coats", 50.0, "https://www.example.com/dress.jpg", "A beautiful dress", 2, "S"),
        Product(6, "Skirt", "pants", 35.0, "https://www.example.com/skirt.jpg", "A lovely skirt", 4, "M"),
    )

    /*
    * Returns a list of products based on the category.
    * If the category is "all", it returns all products.
    * Else if the category is a specific category, it returns products of that category.
    * - If basicInfo is true, it returns a list of products with basic information.
    * - If basicInfo is false, it returns a list of products with all information.
    * */
    fun getProductsByCategory(category: String?, basicInfo: Boolean): List<Product> {
        return if (category == "all") { // Return all products
            modelsLists
        } else { // Return products of a specific category
            modelsLists.filter { it.category == category }.map { product ->
                if (basicInfo) { // Return products with basic information
                    Product(
                        product.id,
                        product.name,
                        product.category,
                        product.price,
                        product.imageUrl,
                        "",
                        0,
                        ""
                    )
                } else {
                    product
                }
            }
        }
    }
}