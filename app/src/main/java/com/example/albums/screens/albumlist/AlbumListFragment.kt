package com.example.albums.screens.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albums.data.Album
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

        val adapter = AlbumsAdapter {
            Toast.makeText(requireContext(), "Album ${it.id} clicked!", Toast.LENGTH_SHORT).show()
        }
        val albums = mutableListOf<Album>()
        repeat(100) {
            albums.add(Album(it.toString(), "Album $it"))
        }
        adapter.submitList(albums)
        binding.albumListList.adapter = adapter
        binding.albumListList.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}
