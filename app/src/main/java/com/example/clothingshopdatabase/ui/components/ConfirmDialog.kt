package com.example.clothingshopdatabase.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.clothingshopdatabase.R

@Composable
fun ConfirmDialog(
    title: String = stringResource(R.string.attention),
    text: String,
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    isAlert: Boolean = true,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = {
            Text(
                text = title
            )
        },
        text = {
            Text(
                text = text
            )
        },
        modifier = modifier,
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(
                    text = if (isAlert) stringResource(R.string.yes) else stringResource(R.string.close)
                )
            }
        },
        dismissButton = {
            if (isAlert)
                Button(onClick = onCancelClick) {
                    Text(stringResource(R.string.no))
                }
        }
    )
}