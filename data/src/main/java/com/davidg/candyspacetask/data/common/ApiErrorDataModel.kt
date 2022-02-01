package com.davidg.candyspacetask.data.common

import com.davidg.candyspacetask.domain.common.DomainMappable
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.squareup.moshi.Json

data class ApiErrorDataModel(
    @Json(name = "error_id")
    val code: Int,
    @Json(name = "error_message")
    val message: String? = null
) : DomainMappable<ErrorModel> {
    override fun asDomain(): ErrorModel = ErrorModel.ServerError(
        this.code,
        this.message
    )
}