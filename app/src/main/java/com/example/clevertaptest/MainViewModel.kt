package com.example.clevertaptest

import androidx.lifecycle.ViewModel
import com.example.clevertap_test_library.CleverTapTest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private val _dogImage = MutableStateFlow<String?>(null)
    val dogImage: StateFlow<String?>
        get() = _dogImage.asStateFlow()

    private val _dogImages = MutableStateFlow<List<String>>(emptyList())
    val dogImages: StateFlow<List<String>>
        get() = _dogImages.asStateFlow()

    fun getSingleImage() {
        val image = CleverTapTest.getImage()
        _dogImage.value = image
    }

    fun getMultipleImages(input: Int) {
        val images = CleverTapTest.getImages(input)
        _dogImages.value = images
    }

    fun getNextImage() {
        val nextImage = CleverTapTest.getNextImage()
        _dogImage.value = nextImage
    }

    fun getPreviousImage() {
        val previousImage = CleverTapTest.getPreviousImage()
        _dogImage.value = previousImage
    }
}