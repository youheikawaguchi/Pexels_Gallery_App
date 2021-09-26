package com.android.y_kawaguchi.pexels_gallery_app.view.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.y_kawaguchi.pexels_gallery_app.R
import com.android.y_kawaguchi.pexels_gallery_app.databinding.FragmentGalleryBinding
import com.android.y_kawaguchi.pexels_gallery_app.repository.model.PexelsPhoto
import com.android.y_kawaguchi.pexels_gallery_app.util.EndlessScroll
import org.koin.androidx.viewmodel.ext.android.viewModel

interface GalleryNavigation {
    fun clickItem(data: PexelsPhoto)
    fun apiError()
}

class GalleryFragment : Fragment(), GalleryNavigation {

    private val viewModel: GalleryViewModel by viewModel()
    private lateinit var mAdapter: GalleryPhotoListItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        with(binding) {
            viewModel = this@GalleryFragment.viewModel
            lifecycleOwner = this@GalleryFragment
            setupRecyclerView(galleryPhotoRecyclerView)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        this@GalleryFragment.viewModel.apply {
                            currentPage = 0
                            nextPage = 0
                            currentQuery.value = query
                            photoList.clear()
                            getSearchData(query, this@GalleryFragment.viewModel.currentPage)
                        }

                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })
        }

        viewModel.isApiStatus.observe(viewLifecycleOwner) {
            if (it) {
                mAdapter.submitList(viewModel.photoList)
                viewModel.isApiStatus.value = false
            }
        }
        viewModel.setNavigation(this)
        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        with(recyclerView) {
            adapter = GalleryPhotoListItem(
                viewModel
            ).also {
                mAdapter = it
            }
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            addOnScrollListener(object : EndlessScroll() {
                override fun onLoadMore() {
                    if (viewModel.isLoading.value == false) {
                        viewModel.currentQuery.value?.let {
                            if (it.isNotBlank() && viewModel.currentPage <= viewModel.nextPage) {
                                viewModel.getSearchData(it, viewModel.nextPage)
                            }
                        }
                    }
                }
            })
        }
    }

    override fun apiError() {
        Toast.makeText(context, R.string.api_error_dialog_title, Toast.LENGTH_SHORT).show()
    }

    override fun clickItem(data: PexelsPhoto) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToPhotoDetailsFragment(data)
        findNavController().navigate(action)
    }
}