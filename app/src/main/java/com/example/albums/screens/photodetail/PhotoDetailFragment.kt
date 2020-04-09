package com.example.albums.screens.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.albums.databinding.FragmentPhotoDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class PhotoDetailFragment : Fragment() {

    private val args by navArgs<PhotoDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotoDetailBinding.inflate(inflater)
        binding.photo = args.selectedPhoto
        return binding.root
    }

}
