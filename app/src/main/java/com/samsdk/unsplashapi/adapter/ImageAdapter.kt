package com.samsdk.unsplashapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samsdk.unsplashapi.databinding.ImageLayoutBinding
import com.samsdk.unsplashapi.model.ImageItem

class ImageAdapter : ListAdapter<ImageItem, ImageAdapter.ImageViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageItem) {
            binding.apply {
                tvDescription.text = item.description

                imageView.load(item.urls.regular) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }
}