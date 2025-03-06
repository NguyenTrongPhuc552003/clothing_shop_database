package com.example.clothingshopdatabase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.model.Product
import com.example.clothingshopdatabase.model.Size
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

@Composable
fun ProductScreen(
    product: Product,
    onAddToCartClick: () -> Unit,
    onBuyNowClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProductTopAppBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProductImage(product.image)
            ProductNameAndPrice(product.name, product.formatPrice())
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xffE9E9E9)
            )
            Description(
                product.description
            )
            SizeList()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProductButton(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.add_to_cart),
                    gradientColors = listOf(Color(0xffC4BBBB), Color(0xff504F4F)),
                    onAddToCartClick = onAddToCartClick
                )
                ProductButton(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.buy_now_button),
                    gradientColors = listOf(Color(0xff15B8B8), Color(0xff0F33E4)),
                    onAddToCartClick = onBuyNowClick
                )
            }
        }
    }
}

@Composable
private fun Description(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp),
    ) {
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = description,
                color = Color(0xff999999),
                style = TextStyle()
            )
        }
    }
}

@Composable
private fun ProductNameAndPrice(
    name: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "VNĐ $price",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun ProductImage(
    image: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xffE9E9E9)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    vertical = 8.dp
                ),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {},
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
fun SizeList(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.size)
        )
        SizeBox(Size.Small.size)
        SizeBox(Size.Medium.size)
        SizeBox(Size.Large.size)
        SizeBox(Size.XLarge.size)
        SizeBox(Size.X2Large.size)
        SizeBox(Size.X3Large.size)
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
            .background(Color(0xffCCCCCC)),
    ) {
        Text(
            text = size,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun ProductButton(
    modifier: Modifier = Modifier,
    title: String,
    gradientColors: List<Color>,
    onAddToCartClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onAddToCartClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Đặt nền trong suốt để dùng gradient
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.horizontalGradient(gradientColors)),
        ) {
            Text(
                text = title
            )
        }
    }
}

//@Preview
//@Composable
//fun P() {
//    ClothingShopDatabaseTheme(darkTheme = false) {
//        ProductScreen(
//            product =
//            Product(
//                1,
//                "T1 World Champions 2024 T-Shirt",
//                "Celebrate T1's historic victory at Worlds 2024" +
//                        " with this limited-edition T-shirt! Made from premium" +
//                        " cotton, it offers maximum comfort for everyday wear." +
//                        " The front features the iconic T1 logo along with the" +
//                        " bold \"World Champions 2024\" text, making it a" +
//                        " must-have for any true fan. Don't miss out on the " +
//                        "chance to own a piece of esports history and show your" +
//                        " support for T1!" +
//                        "T1 World Champions 2024 T-Shirt" +
//                        "Celebrate T1's historic victory at Worlds 2024" +
//                        " with this limited-edition T-shirt! Made from premium" +
//                        " cotton, it offers maximum comfort for everyday wear." +
//                        " The front features the iconic T1 logo along with the" +
//                        " bold \"World Champions 2024\" text, making it a" +
//                        " must-have for any true fan. Don't miss out on the " +
//                        "chance to own a piece of esports history and show your" +
//                        " support for T1!" +
//                        "T1 World Champions 2024 T-Shirt" +
//                        "Celebrate T1's historic victory at Worlds 2024" +
//                        " with this limited-edition T-shirt! Made from premium" +
//                        " cotton, it offers maximum comfort for everyday wear." +
//                        " The front features the iconic T1 logo along with the" +
//                        " bold \"World Champions 2024\" text, making it a" +
//                        " must-have for any true fan. Don't miss out on the " +
//                        "chance to own a piece of esports history and show your" +
//                        " support for T1!" +
//                        "T1 World Champions 2024 T-Shirt" +
//                        "Celebrate T1's historic victory at Worlds 2024" +
//                        " with this limited-edition T-shirt! Made from premium" +
//                        " cotton, it offers maximum comfort for everyday wear." +
//                        " The front features the iconic T1 logo along with the" +
//                        " bold \"World Champions 2024\" text, making it a" +
//                        " must-have for any true fan. Don't miss out on the " +
//                        "chance to own a piece of esports history and show your" +
//                        " support for T1!",
//                R.drawable.t1_2024_t_shirt_wchampions,
//                1000000,
////                listOf(Size.Small, Size.Medium, Size.Large, Size.XLarge, Size.X2Large, Size.X3Large)
//            ),
//            onBackClick = {},
//            onAddToCartClick = {},
//            onBuyNowClick = {}
//        )
//    }
//}
