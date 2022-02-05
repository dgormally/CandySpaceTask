package com.davidg.candyspacetask.ui

import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.davidg.candyspacetask.domain.model.StackUsersModel

sealed class StackListViewState<T>( val data: List<T>? = null,
                                    val error: ErrorModel? = null)
{
    class Loading<T>: StackListViewState<T>()
    class Failure<T>( errorModel: ErrorModel) : StackListViewState<T>(null, errorModel)
    class Success<T>(data: List<T>?= null) : StackListViewState<T>(data)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception]"
            is Loading<T> -> "Loading"
        }
    }
}
