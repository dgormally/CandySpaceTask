package com.davidg.candyspacetask.ui

import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.davidg.candyspacetask.domain.model.StackUsersModel

sealed class StackListViewState{
    object Loading : StackListViewState()
    class Failure(val errorModel: ErrorModel) : StackListViewState()
    class Success(val data: List<StackUsersModel>) : StackListViewState()
    object Empty : StackListViewState()
}
