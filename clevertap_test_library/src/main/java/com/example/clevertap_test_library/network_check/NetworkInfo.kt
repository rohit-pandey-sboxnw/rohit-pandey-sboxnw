package com.example.clevertap_test_library.network_check

import android.net.Network
import kotlinx.coroutines.flow.Flow

internal data class NetworkInfo(
    val isConnected: Boolean,
    internal val network: Network?,
)

internal interface NetworkInfoProvider {
    val networkInfoStream: Flow<NetworkInfo>
}
