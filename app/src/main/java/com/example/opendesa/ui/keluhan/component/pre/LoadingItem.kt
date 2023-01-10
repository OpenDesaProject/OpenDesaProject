package com.example.opendesa.ui.keluhan.component.pre

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.opendesa.theme.Blue

@Composable
fun LoadingItem(
  modifier: Modifier = Modifier
    .fillMaxWidth()
    .wrapContentHeight()
) {
  Box(
    modifier = modifier, contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(
      modifier = Modifier
        .width(42.dp)
        .height(42.dp)
        .padding(8.dp),
      strokeWidth = 3.dp,
      color = Blue
    )
  }
}