package com.example.albums.screens.albumlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.albums.R
import com.example.albums.databinding.FragmentAlbumListBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAlbumListBinding.inflate(inflater)
        return binding.root
    }

}
