package com.davidg.candyspacetask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.domain.usecase.GetUsersUseCase
import com.davidg.candyspacetask.ui.StackListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val usersStateFlow: MutableStateFlow<StackListViewState> =
        MutableStateFlow(StackListViewState.Loading)

    val _usersStateFlow: StateFlow<StackListViewState> = usersStateFlow

    init {
        getUsers()
    }

    fun getUsers(user: String? = null) {
        usersStateFlow.value = StackListViewState.Loading
        viewModelScope.launch {
            getUsersUseCase.execute(user.toString())
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is NetworkResultState.Success -> {
                            if (it.data.isEmpty()) {
                                usersStateFlow.value = StackListViewState.Empty
                            } else {
                                usersStateFlow.value = StackListViewState.Success(it.data)
                            }
                        }
                        is NetworkResultState.Error -> {
                            usersStateFlow.value = StackListViewState.Failure(it.error)
                        }
                        else -> {}
                    }
                }
        }
    }

}