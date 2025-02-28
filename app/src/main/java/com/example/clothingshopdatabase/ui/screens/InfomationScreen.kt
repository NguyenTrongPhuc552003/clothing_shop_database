package com.example.clothingshopdatabase.ui.screens

import android.net.wifi.ScanResult.InformationElement
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.ui.theme.ClothingShopDatabaseTheme

@Composable
fun InfomationScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)) {
        ShopLogo()
        ShopParticipated()
        Description()
        Text(
            text = stringResource(R.string.description_content),
            color = Color.LightGray
        )
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
            .background(Color.LightGray),
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
            .background(Color.LightGray)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.shop_name),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = stringResource(R.string.participated),
            fontStyle = FontStyle.Italic
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

@Preview
@Composable
private fun InformationScreenPreview() {
    ClothingShopDatabaseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            InfomationScreen()
        }
    }
}

