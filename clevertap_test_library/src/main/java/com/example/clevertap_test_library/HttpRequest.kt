package com.example.clevertap_test_library

internal class HttpRequest private constructor(
    val url: String,
    val requestType: HttpRequestType,
    val readTimeOut: Int,
    val connectionTimeOut: Int,
) {
    companion object{
        const val DEFAULT_TIME_OUT = 30000 // 30secs
    }

    internal class HttpRequestBuilder {
        private var url: String = ""
        private var requestType: HttpRequestType = HttpRequestType.GET
        private var readTimeOut: Int = DEFAULT_TIME_OUT
        private var connectionTimeOut: Int = DEFAULT_TIME_OUT

        fun url(url: String): HttpRequestBuilder {
            this.url = url
            return this
        }

        fun requestType(requestType: HttpRequestType): HttpRequestBuilder {
            this.requestType = requestType
            return this
        }

        fun readTimeOut(readTimeOut: Int): HttpRequestBuilder {
            this.readTimeOut = readTimeOut
            return this
        }

        fun connectionTimeOut(connectionTimeOut: Int): HttpRequestBuilder {
            this.connectionTimeOut = connectionTimeOut
            return this
        }

        fun build(): HttpRequest {
            return HttpRequest(
                url = url,
                requestType = requestType,
                readTimeOut = readTimeOut,
                connectionTimeOut = connectionTimeOut,
            )
        }
    }
}

internal enum class HttpRequestType{
    GET
}