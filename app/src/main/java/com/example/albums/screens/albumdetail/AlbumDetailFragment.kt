package com.example.albums.screens.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.example.albums.databinding.FragmentAlbumDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : Fragment() {

    val args by navArgs<AlbumDetailFragmentArgs>()
    val viewModel by viewModels<AlbumDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumDetailBinding.inflate(inflater)
        binding.album = args.selectedAlbum

        val adapter = PhotosAdapter()
        binding.albumDetailPhotoList.adapter = adapter

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}
