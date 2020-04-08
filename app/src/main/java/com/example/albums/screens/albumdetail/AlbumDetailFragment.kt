package com.example.albums.screens.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.albums.DataBindingAdapter
import com.example.albums.data.Photo
import com.example.albums.databinding.FragmentAlbumDetailBinding
import com.example.albums.databinding.PhotoListItemBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : Fragment() {

    val args by navArgs<AlbumDetailFragmentArgs>()
    val viewModel by viewModels<AlbumDetailViewModel>()
    val adapter: DataBindingAdapter<Photo> by lazy {
        DataBindingAdapter({ layoutInflater, parent, attachToRoot ->
            PhotoListItemBinding.inflate(
                layoutInflater,
                parent,
                attachToRoot
            )
        }) { photo: Photo ->
            val binding = this.binding as PhotoListItemBinding
            binding.photo = photo
            binding.root.setOnClickListener {
                viewModel.onSelectedPhoto(photo)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumDetailBinding.inflate(inflater)
        binding.album = args.selectedAlbum

        binding.albumDetailPhotoList.adapter = adapter

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.navigateToSelectedPhoto.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val action =
                    AlbumDetailFragmentDirections.actionAlbumDetailFragmentToPhotoDetailFragment(it)
                findNavController().navigate(action)
            }
        }

        return binding.root
    }

}
