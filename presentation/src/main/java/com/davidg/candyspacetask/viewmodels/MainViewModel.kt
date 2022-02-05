package com.davidg.candyspacetask.viewmodels

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidg.candyspacetask.data.repos.NetworkRepoImpl
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import com.davidg.candyspacetask.domain.usecase.GetUsersUseCase
import com.davidg.candyspacetask.ui.StackListViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.ClassCastException

@ExperimentalCoroutinesApi
class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val usersStateFlow =
        MutableStateFlow<StackListViewState<StackUsersModel>>(StackListViewState.Loading())
    val _usersStateFlow: MutableStateFlow<StackListViewState<StackUsersModel>> get() = usersStateFlow

    init {
        getUsers(pageSize = 20)
    }

    fun getUsers(user: String? = null, pageSize: Int = 0) {
        _usersStateFlow.value = StackListViewState.Loading()
        viewModelScope.launch {
            getUsersUseCase.execute(user.toString(), pageSize)
                .collect {
                    when (it) {
                        is NetworkResultState.Success -> {
                            _usersStateFlow.value = StackListViewState.Success(it.data)
                        }
                        is NetworkResultState.Error -> {
                            _usersStateFlow.value = StackListViewState.Failure(it.error)

                        }
                        else -> {}
                    }
                }
        }
    }
}