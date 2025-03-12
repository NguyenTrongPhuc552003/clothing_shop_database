package com.example.clothingshopdatabase.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.model.Product
import com.example.clothingshopdatabase.ui.components.ConfirmDialog
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

@Composable
fun ProductEntryScreen(
    product: Product,
    onUpdateClick: (Product) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    isValid: Boolean = false,
    isEdit: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isConfirmDialogRequire by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ProductEntryTopAppBar(isEdit,onBackClick)
        },
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            ItemInputForm(
                product = product,
                onUpdateClick = onUpdateClick,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    isConfirmDialogRequire = true
                },
                enabled = isValid,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save_action))
            }
            if (isConfirmDialogRequire){
                ConfirmDialog(
                    text = if (!isEdit) "Are you sure you want to entry ${product.name}?" else "Are you sure you want to edit ${product.name}?",
                    onConfirmClick = {
                        isConfirmDialogRequire = false
                        onSaveClick()
                        onBackClick()
                    },
                    onCancelClick = {
                        isConfirmDialogRequire = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEntryTopAppBar(
    isEdit: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = if (!isEdit) stringResource(R.string.product_entry) else stringResource(R.string.product_edit),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        onBackClick()
                    }
            )
        },
        modifier = modifier
    )
}

@Composable
fun ItemInputForm(
    product: Product,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onUpdateClick: (Product) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = product.name,
            onValueChange = { onUpdateClick(product.copy(name = it)) },
            label = { Text(stringResource(R.string.product_name)) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xff0C6EE2),
                focusedLabelColor = Color(0xff0C6EE2),
                focusedPlaceholderColor = Color(0xff0C6EE2),
                focusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        CategorySelector(
            product = product,
            onUpdateClick = onUpdateClick
        )
        OutlinedTextField(
            value = product.description,
            onValueChange = {  onUpdateClick(product.copy(description = it)) },
            label = { Text(stringResource(R.string.product_description)) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xff0C6EE2),
                focusedLabelColor = Color(0xff0C6EE2),
                focusedPlaceholderColor = Color(0xff0C6EE2),
                focusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            enabled = enabled
        )
        OutlinedTextField(
            value = product.image,
            onValueChange = { onUpdateClick(product.copy(image = it))},
            label = { Text(stringResource(R.string.product_image_link)) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xff0C6EE2),
                focusedLabelColor = Color(0xff0C6EE2),
                focusedPlaceholderColor = Color(0xff0C6EE2),
                focusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = product.price.toString(),
            onValueChange = {
                val price = it.toIntOrNull() ?: 0
                onUpdateClick(product.copy(price = price))
            },
            label = { Text(stringResource(R.string.product_price)) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xff0C6EE2),
                focusedLabelColor = Color(0xff0C6EE2),
                focusedPlaceholderColor = Color(0xff0C6EE2),
                focusedContainerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}

@Composable
fun CategorySelector(
    product: Product,
    onUpdateClick: (Product) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // RadioButton cho "Shirt"
        RadioButton(
            selected = product.category == "shirt",
            onClick = {
                onUpdateClick(product.copy(category = "shirt"))
            }
        )
        Text(text = "Shirt", modifier = Modifier.clickable {
            onUpdateClick(product.copy(category = "shirt"))
        })

        Spacer(modifier = Modifier.width(16.dp)) // Khoảng cách giữa 2 button

        // RadioButton cho "Trousers"
        RadioButton(
            selected = product.category == "trousers",
            onClick = {
                onUpdateClick(product.copy(category = "trousers"))
            }
        )
        Text(text = "Trousers", modifier = Modifier.clickable {
            onUpdateClick(product.copy(category = "trousers"))
        })
    }
}

@Preview
@Composable
fun EntryPreview(){
    ClothingShopDatabaseTheme(darkTheme = false) {
        ProductEntryScreen(
            product = Product(),
            onUpdateClick = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}