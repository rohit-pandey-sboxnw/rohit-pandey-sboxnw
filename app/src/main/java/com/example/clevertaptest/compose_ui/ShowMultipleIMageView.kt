package com.example.clevertaptest.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.clevertaptest.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShowMultipleImageView(viewModel: MainViewModel) {
    val multipleImages = viewModel.dogImages.collectAsState()

    if (multipleImages.value.isNotEmpty()) {
        val pagerState = rememberPagerState()

        Column {
            ImageSliderView(
                multipleImage = multipleImages.value,
                pagerState = pagerState
            )
            Spacer(modifier = Modifier.height(5.dp))
            ComposableDotsIndicator(
                activeIndex = pagerState.currentPage,
                totalSize = multipleImages.value.size
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSliderView(
    multipleImage: List<String>,
    pagerState: PagerState,
) {
    HorizontalPager(
        count = multipleImage.size,
        state = pagerState,
        itemSpacing = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
    ) { pageIndex ->
        val imageUrl = multipleImage[pageIndex]

        Card(
            modifier = Modifier
                .padding(20.dp)
                .aspectRatio(
                    ratio = IMAGE_ASPECT_RATIO,)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            crossfade(true)
                        },
                    ),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    alpha = 1.0f
                )
            }
        }
    }
}