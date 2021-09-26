package com.android.y_kawaguchi.pexels_gallery_app.view.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.y_kawaguchi.pexels_gallery_app.databinding.GalleryPhotoListItemBinding
import com.android.y_kawaguchi.pexels_gallery_app.repository.model.PexelsPhoto
import com.squareup.picasso.Picasso

class GalleryPhotoListItem(
    private val viewModel: GalleryViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemBindingViewHolder(
        val binding: GalleryPhotoListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var currentList = listOf<PexelsPhoto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = GalleryPhotoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ItemBindingViewHolder) return
        val item = this.currentList[position]
        with(holder.binding) {
            data = item
            viewModel = this@GalleryPhotoListItem.viewModel
            executePendingBindings()
            Picasso.get().load(item.src.medium).into(photo)
        }
    }

    override fun getItemCount() = currentList.size

    fun submitList(list: List<PexelsPhoto>) {
        this.currentList = list
        notifyDataSetChanged()
    }

}