package com.example.clevertaptest.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.clevertaptest.MainViewModel
import com.example.clevertaptest.R

@Composable
fun ShowSingleImageView(
    viewModel: MainViewModel,
    onNextButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit
) {
    val singleImage = viewModel.dogImage.collectAsState()

    val isNextButtonClicked = remember { mutableStateOf(false) }

    if (singleImage.value != null) {
        Column(modifier = Modifier
            .height(300.dp)) {
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .aspectRatio(
                        ratio = IMAGE_ASPECT_RATIO,
                    )
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberImagePainter(
                            data = singleImage.value,
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

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        if (isNextButtonClicked.value) {
                            onPreviousButtonClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = if (isNextButtonClicked.value) R.color.purple_500 else R.color.gray))
                ) {
                    Text(
                        text = "Previous",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
                )

                Button(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        isNextButtonClicked.value = true
                        onNextButtonClick()
                    }
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}