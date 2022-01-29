package com.davidg.candyspacetask.data.response

import com.squareup.moshi.Json

class Items(
    @Json(name = "owner")
    val owner: OwnerInfo
)