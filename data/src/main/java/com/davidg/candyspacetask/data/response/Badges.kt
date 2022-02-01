package com.davidg.candyspacetask.data.response

import com.squareup.moshi.Json

class Badges (
    @Json(name = "bronze")
    val bronze: Int,
    @Json(name = "silver")
    val silver: Int,
    @Json(name = "gold")
    val gold: Int
)