package com.davidg.candyspacetask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidg.candyspacetask.domain.usecase.SearchByNameUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel(
    private val searchByNameUseCase: SearchByNameUseCase
) : ViewModel() {

    init {
      //Get default list here
    }

     fun searchUser() {
        viewModelScope.launch {
            searchByNameUseCase.execute(0)
                .collect {

                }
        }
    }
}