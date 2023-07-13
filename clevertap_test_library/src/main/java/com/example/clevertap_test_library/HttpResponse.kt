package com.example.clevertap_test_library

internal data class HttpResponse(
    val statusCode: Int,
    val responseBody: String
) {
    val isSuccess: Boolean
        get() {
            return statusCode in 200..299
        }
}
