/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.eneselcuk.connecttointernet.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.connecttointernet.R
import com.eneselcuk.connecttointernet.network.model.MarsData

class PhotoGridAdapter(private val clickListener: ClickListener) :
    ListAdapter<MarsData, MarsViewHolder>(diffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        return MarsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.grid_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.bind(itemData, clickListener)
    }

    class ClickListener(val onClick: (marsData: MarsData) -> Unit) {
        fun clickListener(marsData: MarsData) = onClick(marsData)
    }
}