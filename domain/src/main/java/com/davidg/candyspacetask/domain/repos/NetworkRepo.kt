package com.davidg.candyspacetask.domain.repos

import com.davidg.candyspacetask.domain.model.UserNameModel
import kotlinx.coroutines.flow.Flow

interface NetworkRepo {

    fun searchUserByName(): Flow<List<UserNameModel>>

}