package com.samsdk.unsplashapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsdk.unsplashapi.model.ImageItem
import com.samsdk.unsplashapi.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject constructor(private val imageRepository: ImageRepository) : ViewModel() {
    private val _response = MutableLiveData<List<ImageItem>>()
    val responseImages: LiveData<List<ImageItem>>
        get() = _response

    init {
        getAllImages()
    }

    private fun getAllImages() = viewModelScope.launch {
        imageRepository.getAllImages().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("ViewModel", "${response.errorBody()}")
            }
        }
    }
}