package com.arthas.nasa.service.api

import com.arthas.nasa.data.model.images.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CuriosityApiService {

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&")
    suspend fun getCuriosityImages(
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ImageResponse

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&")
    suspend fun getCuriosityImagesCam(
        @Query("camera") cam: String,
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ImageResponse

    @GET("mars-photos/api/v1/rovers/opportunity/photos?sol=1000&")
    suspend fun getOpportunityImages(
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ImageResponse

    @GET("mars-photos/api/v1/rovers/opportunity/photos?sol=1000&")
    suspend fun getOpportunityImagesCam(
        @Query("camera") cam: String,
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ImageResponse

    @GET("mars-photos/api/v1/rovers/spirit/photos?sol=1000&")
    suspend fun getSpiritImages(
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ImageResponse

    @GET("mars-photos/api/v1/rovers/spirit/photos?sol=1000&")
    suspend fun getSpiritImagesCam(
        @Query("camera") cam: String,
        @Query("api_key") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ImageResponse

}