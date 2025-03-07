package com.example.clothingshopdatabase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.model.Product

@Composable
fun CartScreen(
    products: List<Product>,
    subTotal: String,
    totalQuantity: String,
    delivery: String,
    totalPrice: String,
    departurePoint: String,
    destination: String,
    onAddItemClick: (Product) -> Unit,
    onRemoveItemClick: (Product) -> Unit,
    onOrderClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CartTopAppBar(onBackClick = onBackClick)
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    ProductButton(
                        modifier = Modifier.heightIn(max = 50.dp),
                        title = stringResource(R.string.order_now),
                        gradientColors = listOf(
                            Color(0xff15BAB8),
                            Color(0xff0F31E6)
                        )
                    ) {
                        onOrderClick()
                    }
                },
                containerColor = Color.White
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ItemList(
                onAddItemClick = onAddItemClick,
                onRemoveItemClick = onRemoveItemClick,
                products = products
            )
            Text(
                text = stringResource(R.string.order_summary),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            OrderSummary(
                subTotal = subTotal,
                totalQuantity = totalQuantity,
                delivery = delivery,
                totalPrice = totalPrice
            )
            Text(
                text = stringResource(R.string.information),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            CartInformation(
                departurePoint = departurePoint,
                destination = destination
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.my_cart),
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
fun CartInformation(
    departurePoint: String,
    destination: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffE9E9E9)
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.name_address),
                color = Color(0xff646464)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.mapaddress_32px),
                    contentDescription = null
                )
                Column {
                    Text(
                        text = departurePoint
                    )
                    HorizontalDivider()
                    Text(
                        text = destination
                    )
                    HorizontalDivider()
                }
            }
            HorizontalDivider()
            Text(
                text = stringResource(R.string.payment_method),
                color = Color(0xff646464)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.cash_32px),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.cash)
                )
            }
        }
    }
}

@Composable
fun OrderSummary(
    subTotal: String,
    totalQuantity: String,
    delivery: String,
    totalPrice: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffE9E9E9)
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.sub_total)
                    )
                    Text(
                        text = stringResource(R.string.total_quantity)
                    )
                    Text(
                        text = stringResource(R.string.delivery)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${subTotal}đ"
                    )
                    Text(
                        text = totalQuantity
                    )
                    Text(
                        text = (if (totalQuantity != "0") delivery else "0") + 'đ'
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xffC7C7C7)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.total),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = (if (totalQuantity != "0") totalPrice else "0") + 'đ',
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ItemList(
    onAddItemClick: (Product) -> Unit,
    onRemoveItemClick:(Product) -> Unit,
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp),
    ) {
        if (products.isNotEmpty())
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(products) {
                    Item(
                        image = it.image,
                        name = it.name,
                        price = it.formatPrice(),
                        size = it.size,
                        stock = it.stock,
                        totalPrice = it.formatTotalPrice(),
                        onSubtractStockClick = {onRemoveItemClick(it)},
                        onAddStockClick = {onAddItemClick(it)},
                    )
                }
            }
        else
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.download_removebg_preview),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
    }
}


@Composable
fun Item(
    image: String,
    name: String,
    price: String,
    size: String,
    stock: Int,
    totalPrice: String,
    onSubtractStockClick: () -> Unit,
    onAddStockClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffE9E9E9)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(70.dp),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img)
            )
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .widthIn(max = 160.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "VNĐ $price",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color(0xff47C6C3)
                        )
                    )
                    SizeBox(
                        size = size
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VNĐ $totalPrice",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xff15BCB7)
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    StockButton(
                        icon = ImageVector.vectorResource(
                            R.drawable.minus_3108
                        ),
                        onClick = onSubtractStockClick
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = stock.toString(),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    StockButton(
                        icon = Icons.Default.Add,
                        onClick = onAddStockClick
                    )
                }
            }
        }
    }
}

@Composable
fun StockButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box( // Bọc trong Box để kiểm soát kích thước tổng thể
        modifier = modifier
            .size(20.dp) // Kích thước tổng thể của nút
            .background(Color.White, CircleShape), // Định dạng nền tròn
        contentAlignment = Alignment.Center // Căn giữa icon
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize() // Điều chỉnh kích thước của nút bấm, không ảnh hưởng icon
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(15.dp), // Kích thước icon
                tint = Color.Black
            )
        }
    }
}

@Composable
fun SizeBox(
    size: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(Color(0xffCCCCCC))
    ) {
        Text(
            text = size,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp),
            color = Color.Black
        )
    }
}

//@Preview
//@Composable
//fun MyPreview() {
//    MaterialTheme {
//        CartScreen(
//            products = DataResource.products,
//            subTotal = "VNĐ 0",
//            totalQuantity = "0",
//            delivery = "VNĐ 0",
//            totalPrice = "VNĐ 0",
//            departurePoint = "Hwang Gyeol",
//            destination = "227 Nguyen Trai Street, W4, D5, HCM City",
//            onOrderClick = {},
//            onBackClick = {}
//        )
//    }
//}