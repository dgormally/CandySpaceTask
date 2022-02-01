package com.davidg.candyspacetask.domain.repos

import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface NetworkRepo {

        fun getUsers(name: String): Flow<NetworkResultState<List<StackUsersModel>>>

}