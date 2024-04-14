package com.example.smarttrade.network.Exception

class NetworkException: Exception {
    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(cause: Throwable): super(cause)
}