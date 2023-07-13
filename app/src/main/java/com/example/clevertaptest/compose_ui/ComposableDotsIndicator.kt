package com.example.clevertaptest.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun ComposableDotsIndicator(
    activeIndex: Int,
    totalSize: Int
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(totalSize) { index ->
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(
                        colorResource(
                            id = when (index == activeIndex) {
                                true -> com.example.clevertaptest.R.color.purple
                                false -> com.example.clevertaptest.R.color.light_purple
                            }
                        )
                    )
            )
            Spacer(modifier = Modifier.width(3.dp))
        }
    }
}
