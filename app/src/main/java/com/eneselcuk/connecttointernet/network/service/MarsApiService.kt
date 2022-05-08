package com.eneselcuk.connecttointernet.network.service

import com.eneselcuk.connecttointernet.network.model.MarsData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApiService {
    @GET("realestate")
    fun getPropertiesAsync(
        @Query("filter") type: String,
    ): Deferred<List<MarsData>>
}