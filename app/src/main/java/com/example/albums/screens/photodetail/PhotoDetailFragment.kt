package com.example.albums.screens.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.albums.databinding.FragmentPhotoDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class PhotoDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotoDetailBinding.inflate(inflater)
        return binding.root
    }

}
