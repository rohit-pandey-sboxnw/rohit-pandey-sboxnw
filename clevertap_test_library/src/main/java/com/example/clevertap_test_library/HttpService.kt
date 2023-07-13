package com.example.clevertap_test_library

import java.io.IOException
import java.net.HttpURLConnection

internal interface HttpService {
    suspend fun makeCall(httpRequest: HttpRequest): HttpResponse

    fun HttpURLConnection.setRequestType(httpRequest: HttpRequest)

    fun HttpURLConnection.setReadTimeOut(httpRequest: HttpRequest)

    fun HttpURLConnection.setConnectionTimeout(httpRequest: HttpRequest)

    @Throws(IOException::class)
    fun HttpURLConnection.retrieveResponseBody(): String
}