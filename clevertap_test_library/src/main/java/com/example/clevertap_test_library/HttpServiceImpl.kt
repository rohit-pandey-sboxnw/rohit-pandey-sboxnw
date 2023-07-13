package com.example.clevertap_test_library

import android.net.Network
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

internal class HttpServiceImpl constructor(
    private val network: Network? = null,
) : HttpService {
    override suspend fun makeCall(httpRequest: HttpRequest): HttpResponse {
        val requestUrl = URL(httpRequest.url)
        val connection =
            if (network != null) network.openConnection(requestUrl) else requestUrl.openConnection()
        return with(connection as HttpURLConnection) {

            val _httpRequest: HttpRequest = httpRequest

            setRequestType(_httpRequest)
            setReadTimeOut(_httpRequest)
            setConnectionTimeout(_httpRequest)

            var responseBody = ""
            var statusCode = -1

            try {
                statusCode = responseCode
                responseBody = retrieveResponseBody()
            } finally {
                disconnect()
            }

            val _httpResponse = HttpResponse(
                statusCode = statusCode,
                responseBody = responseBody
            )
            _httpResponse
        }
    }

    override fun HttpURLConnection.setRequestType(httpRequest: HttpRequest) {
        requestMethod = httpRequest.requestType.name
    }

    override fun HttpURLConnection.setReadTimeOut(httpRequest: HttpRequest) {
        readTimeout = httpRequest.readTimeOut
    }

    override fun HttpURLConnection.setConnectionTimeout(httpRequest: HttpRequest) {
        connectTimeout = httpRequest.connectionTimeOut
    }

    override fun HttpURLConnection.retrieveResponseBody(): String {
        var responseBody = ""

        try {
            inputStream?.let {
                responseBody = inputStream.bufferedReader().use(BufferedReader::readText)
            }
        } catch (ioException: IOException) {
            try {
                if (errorStream == null) {
                    throw ioException
                }
                errorStream?.let {
                    responseBody = it.bufferedReader().use(BufferedReader::readText)
                }
            } catch (ioException: IOException) {
                throw ioException
            }
        }
        return responseBody
    }
}

internal object HttpServiceFactory {
    fun create(
        network: Network? = null,
    ): HttpService {
        return HttpServiceImpl(network)
    }
}