package com.samsdk.unsplashapi.api

import com.samsdk.unsplashapi.model.ImageItem
import com.samsdk.unsplashapi.util.Constants.CLIENT_ID
import com.samsdk.unsplashapi.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")

    @GET(END_POINT)
    suspend fun getAllImages(): Response<List<ImageItem>>
}