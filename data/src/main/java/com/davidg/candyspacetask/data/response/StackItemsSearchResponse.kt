package com.davidg.candyspacetask.data.response

import com.davidg.candyspacetask.domain.common.DomainMappable
import com.davidg.candyspacetask.domain.model.UserNameModel
import com.squareup.moshi.Json

class StackItemsSearchResponse(
    @Json(name = "items")
    val items: List<Items>
): DomainMappable<List<UserNameModel>> {
    override fun asDomain(): List<UserNameModel> {
        val mutableList = mutableListOf<UserNameModel>()
        items.forEach {
            mutableList.add(
                UserNameModel(
                    it.owner.display_name
                )
            )
        }
        return mutableList
    }
}