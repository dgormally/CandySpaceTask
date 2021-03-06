package com.davidg.candyspacetask.domain.model

sealed class ErrorModel {

    // Generic network errors
    sealed class NetworkError : ErrorModel() {
        object Network : NetworkError()
        object Timeout : NetworkError()
        object ServerUnavailable : NetworkError()
    }

    // Generic HTTP Error 400+
    data class ServerError(
        val code: Int,
        val message: String? = null
    ) : ErrorModel()

    data class Unknown(val msg: String = "") : ErrorModel()
}
