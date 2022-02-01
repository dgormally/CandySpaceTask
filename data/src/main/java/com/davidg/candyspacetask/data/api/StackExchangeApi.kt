package com.davidg.candyspacetask.data.api

import com.davidg.candyspacetask.data.common.ApiErrorDataModel
import com.davidg.candyspacetask.data.response.StackItemsSearchResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1,
                         @Query("pagesize") pageSize: Int? = 20,
                         @Query("order") order: String? = "asc",
                         @Query("sort") sort: String? = "name",
                         @Query("inname") inname: String?,
                         @Query("site") site: String? = "stackoverflow"): NetworkResponse<StackItemsSearchResponse, ApiErrorDataModel>
}