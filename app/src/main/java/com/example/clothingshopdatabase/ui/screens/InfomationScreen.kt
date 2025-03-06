package com.example.clothingshopdatabase.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

@Composable
fun InformationScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { InformationTopAppBar(onBackClick) }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            ) {
                ShopLogo()
            }
            ShopParticipated()
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xffE9E9E9)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Description()
                Text(
                    text = stringResource(R.string.description_content),
                    color = Color(0xff999999),
                    style = TextStyle()
                )
                ImageWithTextRow(
                    R.drawable.product_32px,
                    stringResource(R.string.product_number)
                )
                ImageWithTextRow(
                    R.drawable.mapaddress_32px,
                    stringResource(R.string.address)
                )
                ImageWithTextRow(
                    R.drawable.verified_32px,
                    stringResource(R.string.verified_stores)
                )
            }
        }
    }
}

@Composable
private fun ShopLogo(
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
        Image(
            painter = painterResource(R.drawable.logo_p2q_store),
            contentDescription = stringResource(R.string.shop_logo),
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    vertical = 8.dp
                )
        )
    }
}

@Composable
private fun ShopParticipated(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)) // Bo tròn cả Row
            .background(Color(0xffE9E9E9))
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.shop_name),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.participated),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight(500)
            ),
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(R.drawable.user_32px),
            contentDescription = stringResource(R.string.user),
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
private fun ShopInformation(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun Description(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.description_32px),
            contentDescription = stringResource(R.string.description),
            modifier = Modifier
                .padding(end = 16.dp)
                .size(30.dp)
        )
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun ImageWithTextRow(
    @DrawableRes image: Int,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image), // Thay bằng ảnh của bạn
            contentDescription = "Sample Image",
            modifier = Modifier
                .size(32.dp) // Kích thước cố định
        )

        Spacer(modifier = Modifier.width(16.dp)) // Khoảng cách giữa ảnh và text

        Box(
            modifier = Modifier
                .weight(1f) // Để text có thể co dãn
                .alignByBaseline(), // Căn chỉnh theo baseline của ảnh

        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationTopAppBar(
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.shop_information),
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
        }
    )
}


