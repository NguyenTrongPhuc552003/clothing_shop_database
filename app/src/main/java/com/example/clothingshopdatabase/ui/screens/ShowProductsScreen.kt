package com.example.clothingshopdatabase.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clothingshopdatabase.model.Product

@Composable
fun ShowProductsScreen(
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            InformationTopAppBar(onBackClick)
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 cột
            modifier = modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(products) { product ->
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .widthIn(max = 180.dp)
                        .clickable { onItemClick(product.id) }
                ) {
                    PopularProductItem(
                        product = product,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
