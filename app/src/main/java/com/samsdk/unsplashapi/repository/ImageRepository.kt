package com.samsdk.unsplashapi.repository

import com.samsdk.unsplashapi.api.ImageService
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: ImageService
) {
    suspend fun getAllImages() = api.getAllImages()
}