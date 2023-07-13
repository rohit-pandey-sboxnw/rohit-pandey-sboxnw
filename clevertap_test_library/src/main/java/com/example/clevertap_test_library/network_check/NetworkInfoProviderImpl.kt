package com.example.clevertap_test_library.network_check

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

internal class NetworkInfoProviderImpl(
    private val connectivityManager: ConnectivityManager,
): NetworkInfoProvider {
    override val networkInfoStream: Flow<NetworkInfo> = callbackFlow {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkInfo = NetworkInfo(
                    isConnected = true,
                    network = network,
                )
                trySend(networkInfo)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                val networkInfo = NetworkInfo(
                    isConnected = false,
                    network = network,
                )
                trySend(networkInfo)
            }
        }
        connectivityManager.registerNetworkCallback(request, networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}