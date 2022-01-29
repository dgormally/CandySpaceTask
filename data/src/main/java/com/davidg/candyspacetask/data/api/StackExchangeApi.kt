package com.davidg.candyspacetask.data.api

import com.davidg.candyspacetask.data.response.StackItemsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
interface StackExchangeApi {

    @GET("search/advanced?order=desc&sort=activity&user=John&site=stackoverflow")
    suspend fun searchUserByName(): StackItemsSearchResponse

}