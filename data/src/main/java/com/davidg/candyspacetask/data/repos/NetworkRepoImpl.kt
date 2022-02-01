package com.davidg.candyspacetask.data.repos
import com.davidg.candyspacetask.data.api.StackExchangeApi
import com.davidg.candyspacetask.data.common.mapToDomain
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NetworkRepoImpl(private val stackExchangeApi: StackExchangeApi): NetworkRepo{


    override fun getUsers(username: String): Flow<NetworkResultState<List<StackUsersModel>>> =
        flow {
            emit(stackExchangeApi.getUsers(inname = username).mapToDomain())
        }.flowOn(Dispatchers.IO)
}


