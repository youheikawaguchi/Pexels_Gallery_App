package com.android.y_kawaguchi.pexels_gallery_app.view.gallery.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.y_kawaguchi.pexels_gallery_app.databinding.FragmentPhotoDetailsBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoDetailsFragment : Fragment() {

    private val viewModel: PhotoDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)

        with(binding) {
            arguments?.let {
                val args = PhotoDetailsFragmentArgs.fromBundle(it)
                data = args.data
                Picasso.get().load(args.data.src.large).into(photo)
            }
            viewModel = this@PhotoDetailsFragment.viewModel
        }
        return binding.root
    }
}