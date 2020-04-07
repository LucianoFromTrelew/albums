package com.example.albums.screens.albumdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.albums.R
import com.example.albums.databinding.FragmentAlbumDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : Fragment() {

    val args by navArgs<AlbumDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumDetailBinding.inflate(inflater)
        binding.album = args.selectedAlbum
        return binding.root
    }

}
