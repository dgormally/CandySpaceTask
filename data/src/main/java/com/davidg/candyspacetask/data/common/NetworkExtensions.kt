package com.davidg.candyspacetask.data.common

import com.davidg.candyspacetask.domain.common.DomainMappable
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.haroldadmin.cnradapter.NetworkResponse
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T : DomainMappable<R>, U : DomainMappable<ErrorModel>, R : Any> NetworkResponse<T, U>.mapToDomain(): NetworkResultState<R> =
    when (this) {
        is NetworkResponse.Success<T> -> NetworkResultState.Success(this.body.asDomain())
        is NetworkResponse.ServerError -> NetworkResultState.Error(this.body!!.asDomain())
        is NetworkResponse.NetworkError -> NetworkResultState.Error(getError(this.error))
        is NetworkResponse.UnknownError -> NetworkResultState.Error(getError(this.error))
    }

fun getError(throwable: Throwable): ErrorModel = when (throwable) {
    is IOException -> ErrorModel.NetworkError.Network
    is ConnectException -> ErrorModel.NetworkError.Network
    is SocketTimeoutException -> ErrorModel.NetworkError.Timeout
    is UnknownHostException -> ErrorModel.NetworkError.ServerUnavailable
    is InterruptedIOException -> ErrorModel.NetworkError.Network
    else -> ErrorModel.Unknown(throwable.message.toString())
}