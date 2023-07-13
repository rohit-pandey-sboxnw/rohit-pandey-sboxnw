package com.example.clevertap_test_library

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("message") val message: List<String>,
    @SerialName("status") val status: String
)
