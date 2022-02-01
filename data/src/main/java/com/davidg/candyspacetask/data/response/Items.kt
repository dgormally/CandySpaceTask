package com.davidg.candyspacetask.data.response

import com.squareup.moshi.Json

class Items(

    @Json(name = "badge_counts")
    val badges: Badges,

    @Json(name = "account_id")
    val account_id: Int,

    @Json(name = "display_name")
    val display_name: String? = null,

    @Json(name = "profile_image")
    val avatar: String,

    @Json(name = "location")
    val location: String? = null,

    @Json(name = "creation_date")
    val creation_date: Long,

    @Json(name = "reputation")
    val reputation: Int
)