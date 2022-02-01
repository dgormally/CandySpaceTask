package com.davidg.candyspacetask.domain.common

import com.davidg.candyspacetask.domain.model.ErrorModel


sealed class NetworkResultState<out T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResultState<T>()
    data class Error(val error: ErrorModel) : NetworkResultState<Nothing>()

}
