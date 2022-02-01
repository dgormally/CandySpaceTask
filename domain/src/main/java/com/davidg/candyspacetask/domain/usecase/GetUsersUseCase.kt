package com.davidg.candyspacetask.domain.usecase

import com.davidg.candyspacetask.domain.common.BaseUseCase
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val networkRepo: NetworkRepo) :
    BaseUseCase<String, Flow<NetworkResultState<List<StackUsersModel>>>>() {

    override fun execute(params: String): Flow<NetworkResultState<List<StackUsersModel>>> {
        return networkRepo.getUsers(params)
    }
}