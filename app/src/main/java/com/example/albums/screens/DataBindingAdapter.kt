package com.example.albums.screens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private typealias InflaterFunction = (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ViewDataBinding

class DataBindingAdapter<I : DataBindingAdapter.HasId>(
    private val inflater: InflaterFunction,
    private val binder: ViewHolder.(I) -> Unit
) :
    ListAdapter<I, DataBindingAdapter.ViewHolder>(
        DiffCallback()
    ) {

    class DiffCallback<I : HasId> : DiffUtil.ItemCallback<I>() {
        override fun areItemsTheSame(oldItem: I, newItem: I): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: I, newItem: I): Boolean {
            return oldItem == newItem
        }
    }

    interface HasId {
        val id: String
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent,
            inflater
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(getItem(position))
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, inflate: InflaterFunction): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

}