package com.samsdk.unsplashapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsdk.unsplashapi.adapter.ImageAdapter
import com.samsdk.unsplashapi.databinding.ActivityMainBinding
import com.samsdk.unsplashapi.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        imageAdapter = ImageAdapter()
        setupRecyclerView()
        callApi()
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        adapter = imageAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
    }

    private fun callApi() {
        viewModel.responseImages.observe(this) { response ->
            if (response != null) {
                imageAdapter.submitList(response)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}