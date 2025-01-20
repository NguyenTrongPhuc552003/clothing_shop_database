package com.example.clothingshopdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the database helper
        val dbHelper = DatabaseHelper(this)

        setContent {
            ClothingShopDatabaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainScreen(
                            dbHelper = dbHelper,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(dbHelper: DatabaseHelper, modifier: Modifier = Modifier) {
    var categoryName by remember { mutableStateOf("") }
    var categoryDescription by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Category", style = MaterialTheme.typography.titleMedium)

        BasicTextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(8.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = categoryDescription,
            onValueChange = { categoryDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(8.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    innerTextField()
                }
            }
        )

        Button(
            onClick = {
                val categoryId = insertCategory(dbHelper, categoryName, categoryDescription)
                resultMessage = if (categoryId != -1L) {
                    "Category added with ID: $categoryId"
                } else {
                    "Failed to add category"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Category")
        }

        Text(resultMessage)
    }
}

private fun insertCategory(dbHelper: DatabaseHelper, name: String, description: String): Long {
    val db = dbHelper.writableDatabase
    val values = android.content.ContentValues().apply {
        put(DatabaseHelper.COLUMN_CATEGORY_NAME, name)
        put(DatabaseHelper.COLUMN_CATEGORY_DESCRIPTION, description)
    }
    val categoryId = db.insert(DatabaseHelper.TABLE_CATEGORIES, null, values)
    if (categoryId != -1L) {
        Log.d("Database", "Category inserted with ID: $categoryId")
    } else {
        Log.e("Database", "Failed to insert category")
    }
    return categoryId
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ClothingShopDatabaseTheme {
        MainScreen(dbHelper = DatabaseHelper(null))
    }
}
