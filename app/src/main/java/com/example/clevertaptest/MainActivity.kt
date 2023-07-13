package com.example.clevertaptest

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.clevertap_test_library.CleverTapTest
import com.example.clevertaptest.compose_ui.ShowDogDetailsView

class MainActivity: ComponentActivity() {
    private val connectivityManager by lazy {
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CleverTapTest.init(connectivityManager)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            ShowDogDetailsView(
                viewModel = viewModel,
                onShowImageClick = {
                    viewModel.getSingleImage()
                },
                onSubmitButtonClick = {
                    viewModel.getMultipleImages(it)
                },
                onNextButtonClick = { viewModel.getNextImage() },
                onPreviousButtonClick = { viewModel.getPreviousImage() }
            )
        }
    }
}