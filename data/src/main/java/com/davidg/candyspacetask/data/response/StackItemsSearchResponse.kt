package com.davidg.candyspacetask.data.response

import com.davidg.candyspacetask.domain.common.DomainMappable
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.squareup.moshi.Json

class StackItemsSearchResponse(
    @Json(name = "items")
    val items: List<Items>
): DomainMappable<List<StackUsersModel>> {
    override fun asDomain(): List<StackUsersModel> {
        val mutableList = mutableListOf<StackUsersModel>()
        items.forEach {
            mutableList.add(
                StackUsersModel(
                    it.account_id, it.display_name.toString(),
                    it.avatar, it.location.toString(),
                    it.creation_date, it.reputation,
                    it.badges.bronze, it.badges.gold, it.badges.silver
                )
            )
        }
        return mutableList
    }
}