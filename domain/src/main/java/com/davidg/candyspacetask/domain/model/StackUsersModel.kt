package com.davidg.candyspacetask.domain.model

import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class StackUsersModel(
    val accountID: Int,
    val userName: String,
    val avatar: String,
    val location: String? = null,
    val creation_date: Long,
    val reputation: Int,
    val noOfBronze: Int,
    val noOfGold: Int,
    val noOfSilver: Int,
) : Serializable
