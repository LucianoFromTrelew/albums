package com.example.albums.screens.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.albums.databinding.FragmentAlbumListBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumListFragment : Fragment() {

    val viewModel: AlbumListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumListBinding.inflate(inflater)

        val adapter = AlbumsAdapter(AlbumsAdapter.ClickListener {
            val action =
                AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(it)
            findNavController().navigate(action)
        })
        binding.albumListList.adapter = adapter

        viewModel.albums.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }


}
