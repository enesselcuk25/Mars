package com.eneselcuk.connecttointernet.overview.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.connecttointernet.databinding.GridViewItemBinding
import com.eneselcuk.connecttointernet.network.model.MarsData

class MarsViewHolder(val binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: MarsData,
        clickListener: PhotoGridAdapter.ClickListener,
    ) {
        binding.marsData = data
        binding.click = clickListener
        binding.executePendingBindings()
    }
}