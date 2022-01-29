package com.davidg.candyspacetask.domain.usecase

import com.davidg.candyspacetask.domain.common.BaseUseCase
import com.davidg.candyspacetask.domain.model.UserNameModel
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import kotlinx.coroutines.flow.Flow

class SearchByNameUseCase(private val networkRepo: NetworkRepo) :
    BaseUseCase<Int, Flow<Any>>() {

    override fun execute(params: Int): Flow<List<UserNameModel>> {
        return networkRepo.searchUserByName()
    }
}