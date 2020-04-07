package com.example.albums.screens.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.data.Album
import com.example.albums.databinding.AlbumListItemBinding

class AlbumsAdapter(private val clickListener: ClickListener) :
    ListAdapter<Album, AlbumsAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, clickListener)
    }

    class ViewHolder private constructor(val binding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            album: Album,
            clickListener: ClickListener
        ) {
            binding.album = album
            binding.root.setOnClickListener {
                clickListener.onClick(album)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    class ClickListener(private val clickListener: (Album) -> Unit){
        fun onClick(album: Album) = clickListener(album)
    }
}