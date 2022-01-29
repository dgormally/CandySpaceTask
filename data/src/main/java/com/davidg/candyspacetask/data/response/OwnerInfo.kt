package com.davidg.candyspacetask.data.response

import com.squareup.moshi.Json

class OwnerInfo(
    @Json(name = "display_name")
    val display_name: String
)