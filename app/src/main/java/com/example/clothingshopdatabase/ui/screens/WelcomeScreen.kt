package com.example.clothingshopdatabase.ui.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.model.Product

@Composable
fun WelcomeScreen(
    onInformationClick: () -> Unit,
    onCartClick: () -> Unit,
    onAddProductClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onAllClick: () -> Unit,
    onTrousersClick: () -> Unit,
    onShirtClick: () -> Unit,
    products: List<Product>
) {
    Scaffold(
        topBar = { WelcomeTopBar() },
        bottomBar = {
            CustomBottomBar(
                onInformationClick = onInformationClick,
                onCartClick = onCartClick,
                onAddClick = onAddProductClick
            )
        }
    ) {
        WelcomeContent(
            modifier = Modifier.padding(it),
            products = products,
            onItemClick = onItemClick,
            onTrousersClick = onTrousersClick,
            onShirtClick = onShirtClick,
            onAllClick = onAllClick
        )
    }
}

@Composable
private fun WelcomeContent(
    onShirtClick: () -> Unit,
    onTrousersClick: () -> Unit,
    onAllClick: () -> Unit,
    modifier: Modifier = Modifier,
    products: List<Product>,
    onItemClick: (Int) -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DiscountImageBox(
                onClick = {}
            )
            Text(
                text = stringResource(R.string.categories),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Categories(
                onShirtClick = onShirtClick,
                onTrousersClick = onTrousersClick,
                onAllClick = onAllClick
            )
            PopularProductTextRow()
        }
        PopularProduct(
            modifier = Modifier
                .fillMaxWidth(),
            products = products,
            onItemClick = onItemClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WelcomeTopBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(Color(0xFF0F2FE4))
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF0F2FE4)
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_p2q_store_radius),
                        contentDescription = stringResource(R.string.shop_logo),
                        modifier = Modifier.size(50.dp)
                    )
                    Column(
                        modifier = Modifier.width(150.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.welcome_to),
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight(300),
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(R.string.shop_name),
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 25.sp
                        )
                    }
                }
            }
        )
        SearchBar()
    }
}


@Composable
private fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon)
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.search_anything_you_want_to_buy))
        },
        singleLine = true
    )
}

@Composable
fun CustomBottomBar(
    onCartClick: () -> Unit,
    onInformationClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            val width = size.width
            val height = size.height
            val cornerRadius = 16.dp.toPx()
            val curveDepth = 32.dp.toPx()
            val curveDiameter = 64.dp.toPx()

            val path = Path().apply {
                // Bắt đầu từ góc trái dưới
                moveTo(0f, height)

                // Vẽ đến góc trái trên
                lineTo(0f, cornerRadius)
                quadraticTo(0f, 0f, cornerRadius, 0f)

                // Vẽ đến điểm bắt đầu của phần lõm
                lineTo(width / 2 - curveDiameter / 2, 0f)

                // Vẽ phần lõm
                quadraticTo(
                    width / 2, 0f,  // điểm điều khiển
                    width / 2, curveDepth  // điểm đích
                )
                quadraticTo(
                    width / 2, 0f,  // điểm điều khiển
                    width / 2 + curveDiameter / 2, 0f  // điểm đích
                )

                // Vẽ đến góc phải
                lineTo(width - cornerRadius, 0f)
                quadraticTo(width, 0f, width, cornerRadius)

                // Hoàn thành path
                lineTo(width, height)
                close()
            }

            drawPath(
                path = path,
                color = Color.White
            )
        }

        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            containerColor = Color.Transparent,
            tonalElevation = 8.dp,
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // Nút Cart bên trái
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(onClick = onCartClick) {
                            Icon(
                                painter = painterResource(R.drawable.cart_32px),
                                contentDescription = "Cart",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("Cart", fontSize = 15.sp)
                    }

                    // Khoảng trống cho FAB
                    Spacer(modifier = Modifier.weight(1f))

                    // Nút About us bên phải
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(onClick = onInformationClick) {
                            Icon(
                                Icons.Default.Info,
                                contentDescription = "About us",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("About us", fontSize = 15.sp)
                    }
                }
            }
        )

        FloatingActionButton(
            onClick = onAddClick,
            containerColor = Color.Blue,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-32).dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Home",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
private fun PopularProductTextRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.popular_product),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.flame),
            contentDescription = stringResource(R.string.flame_image),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = stringResource(R.string.hot),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            color = Color(0xFFFF5722),
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun DiscountImageBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.chillguy),
            contentDescription = stringResource(R.string.chillGuyImage),
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
                .offset(y = 115.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Discount 20% For Popular Product",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.widthIn(max = 150.dp)
            )
            OutlinedButton(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4)
                )
            ) {
                Text(stringResource(R.string.buy_now))
            }
        }
    }
}

@Composable
private fun Categories(
    onShirtClick: () -> Unit,
    onTrousersClick: () -> Unit,
    onAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CategoriesItem(
            image = R.drawable.tshirt_32px,
            imageDescription = R.string.tshirt_image,
            itemName = R.string.coats,
            onClick = onShirtClick
        )
        CategoriesItem(
            image = R.drawable.pants_32px,
            imageDescription = R.string.pants_image,
            itemName = R.string.pants,
            onClick = onTrousersClick
        )
        CategoriesItem(
            image = R.drawable.all_32px,
            imageDescription = R.string.allProducts_image,
            itemName = R.string.allProducts,
            onClick = onAllClick
        )
    }
}

@Composable
private fun CategoriesItem(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes imageDescription: Int,
    @StringRes itemName: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(imageDescription),
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = stringResource(itemName)
        )
    }
}

@Composable
private fun PopularProduct(
    products: List<Product>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(vertical = 16.dp)
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) {
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
                    .clickable { onItemClick(it.id) }
            ) {
                PopularProductItem(product = it)
            }
        }
    }
}

@Composable
fun PopularProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    var isSingleLine by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
    ) {
        ImageBox(
            item = product
        )
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xA82F2D2D),
            onTextLayout = { textLayoutResult ->
                isSingleLine = textLayoutResult.lineCount == 1
            },
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = if (isSingleLine) 28.dp else 8.dp// Thay đổi padding dựa trên số dòng
                )
        )
        Text(
            text = "${product.formatPrice()}đ",
            color = Color(0xFF00BCD4),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        )
    }
}

@Composable
private fun ImageBox(
    modifier: Modifier = Modifier,
    item: Product
) {
    Box(
        modifier = modifier
            .size(200.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(item.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img)
        )
        Image(
            painter = painterResource(R.drawable.logo_p2q_store_radius),
            contentDescription = stringResource(R.string.small_image),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}
