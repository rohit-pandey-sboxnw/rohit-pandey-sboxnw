package com.example.clevertaptest.compose_ui

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.clevertaptest.MainViewModel

internal const val IMAGE_ASPECT_RATIO = 1.77F

@Composable
fun ShowDogDetailsView(
    viewModel: MainViewModel,
    onShowImageClick: () -> Unit,
    onSubmitButtonClick: (Int) -> Unit,
    onNextButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit
) {
    val singleImageButtonState = remember { mutableStateOf(true) }
    val inputValue = remember { mutableStateOf("") }

    val context = LocalContext.current.applicationContext

    Column(
        Modifier
            .background(Color.Transparent)
            .fillMaxHeight()) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        )
        if (singleImageButtonState.value) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    onShowImageClick()
                    singleImageButtonState.value = false
                }
            ) {
                Text(
                    text = "Show Image",
                    color = Color.White
                )
            }
        }

        ShowSingleImageView(
            viewModel = viewModel,
            onNextButtonClick = onNextButtonClick,
            onPreviousButtonClick = onPreviousButtonClick
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
        )

        TextField(
            value = inputValue.value,
            onValueChange = { newInput ->
                if (newInput.length <= 2) {
                    inputValue.value = newInput
                }
            },
            label = { Text(text = "Number")},
            placeholder = { Text(text = "Enter a number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                when(inputValue.value) {
                    "" -> showToast(context)
                    else -> {
                        when (inputValue.value.toInt() in 1..10) {
                            true -> onSubmitButtonClick(inputValue.value.toInt())
                            false -> showToast(context)
                        }
                    }
                }
            },
        ) {
            Text(
                text = "Submit",
                color = Color.White
            )
        }

        ShowMultipleImageView(viewModel = viewModel)
    }
}

private fun showToast(context: Context) =
    Toast.makeText(context, "Please enter number in the range of 1 to 10", LENGTH_LONG).show()