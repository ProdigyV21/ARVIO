package com.arflix.tv.data.api

import com.arflix.tv.data.model.CloudStreamExtensionManifest
import com.arflix.tv.data.model.CloudStreamPluginList
import com.arflix.tv.data.model.CloudStreamSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CloudStreamApi {

    @GET
    suspend fun getRepositoryManifest(@Url url: String): CloudStreamExtensionManifest

    @GET
    suspend fun getPluginList(@Url url: String): CloudStreamPluginList

    @GET
    suspend fun search(
        @Url url: String,
        @Query("query") query: String
    ): CloudStreamSearchResponse

    @GET
    suspend fun searchRaw(@Url url: String): CloudStreamSearchResponse
}
