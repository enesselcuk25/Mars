package com.eneselcuk.connecttointernet.overview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.connecttointernet.network.model.MarsData

val diffUtilCallBack = object : DiffUtil.ItemCallback<MarsData>() {
    override fun areItemsTheSame(oldItem: MarsData, newItem: MarsData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarsData, newItem: MarsData): Boolean =
        oldItem == newItem
}