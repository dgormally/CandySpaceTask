package com.davidg.candyspacetask.data.repos

import com.davidg.candyspacetask.data.api.StackExchangeApi
import com.davidg.candyspacetask.domain.model.UserNameModel
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NetworkRepoImpl(private val stackExchangeApi: StackExchangeApi): NetworkRepo{

    override fun searchUserByName(): Flow<List<UserNameModel>> =
        flow {
            emit(stackExchangeApi.searchUserByName().asDomain())
        }.flowOn(Dispatchers.IO)
}


