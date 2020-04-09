package com.example.albums.screens.albumlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.albums.DataBindingAdapter
import com.example.albums.data.domain.Album
import com.example.albums.databinding.AlbumListItemBinding
import com.example.albums.databinding.FragmentAlbumListBinding
import com.example.albums.utils.appComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AlbumListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AlbumListViewModel by viewModels { viewModelFactory }
    private val adapter: DataBindingAdapter<Album> by lazy {
        DataBindingAdapter({ layoutInflater, viewGroup, attachToRoot ->
            AlbumListItemBinding.inflate(
                layoutInflater,
                viewGroup,
                attachToRoot
            )
        }) { album: Album ->
            val binding = this.binding as AlbumListItemBinding
            binding.album = album
            binding.root.setOnClickListener {
                viewModel.onAlbumClick(album)
            }
            binding.executePendingBindings()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.albumListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumListBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.albumListList.adapter = adapter

        viewModel.albums.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.navigateToSelectedAlbum.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val action =
                    AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(it)
                findNavController().navigate(action)
            }
        }

        return binding.root

    }

}
