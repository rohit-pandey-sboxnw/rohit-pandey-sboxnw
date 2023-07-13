package com.example.clevertap_test_library

import android.net.ConnectivityManager
import android.net.Network
import com.example.clevertap_test_library.HttpRequest.Companion.DEFAULT_TIME_OUT
import com.example.clevertap_test_library.network_check.NetworkInfoProvider
import com.example.clevertap_test_library.network_check.NetworkInfoProviderImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

class CleverTapTest private constructor() {
    companion object: CoroutineScope {
        private val serializer = Json { ignoreUnknownKeys = true }

        private var dogsList: List<String> = emptyList()
        private var previousDogsList: ArrayList<String> = arrayListOf()
        private var counter = 1

        @JvmStatic
        fun init(connectivityManager: ConnectivityManager) {
            val networkInfoProvider: NetworkInfoProvider =
                NetworkInfoProviderImpl(connectivityManager)

            startNetworkObservation(networkInfoProvider)
        }

        private fun startNetworkObservation(networkInfoProvider: NetworkInfoProvider) {
            launch {
                networkInfoProvider.networkInfoStream.collect {
                    fetchDetails(it.network)
                }
            }
        }

        private suspend fun fetchDetails(network: Network?) = withContext(Dispatchers.IO) {
            val url = "https://dog.ceo/api/breeds/image/random/30"
            val request = HttpRequest.HttpRequestBuilder()
                .url(url)
                .requestType(HttpRequestType.GET)
                .readTimeOut(DEFAULT_TIME_OUT)
                .connectionTimeOut(DEFAULT_TIME_OUT)
                .build()

            val response = HttpServiceFactory.create(network).makeCall(request)
            if (response.isSuccess) {
                val responseBody = response.responseBody
                val apiResponse = decodeFromResponse(responseBody)
                dogsList = apiResponse.message
            }
        }

        private suspend fun decodeFromResponse(data: String) = withContext(Dispatchers.IO) {
            serializer.decodeFromString(ApiResponse.serializer(), data)
        }

        fun getImage(): String {
            val firstImage = dogsList.first()
            previousDogsList.add(firstImage)
            return firstImage
        }
        fun getImages(input: Int) = dogsList.asSequence().shuffled().take(input).toList()
        fun getNextImage(): String? {
            counter = 1
            val nextRandomImage = dogsList.asSequence().shuffled().find { true }
            if (nextRandomImage != null) {
                previousDogsList.add(nextRandomImage)
            }
            return nextRandomImage
        }
        fun getPreviousImage(): String {
            counter += 1
            val previousImage: String = if ((previousDogsList.size - counter) >= 0) {
                previousDogsList[previousDogsList.size - counter]
            } else { previousDogsList.first() }
            return previousImage
        }

        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Dispatchers.Main
    }
}