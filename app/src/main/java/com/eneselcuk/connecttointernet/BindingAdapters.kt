package com.eneselcuk.connecttointernet

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eneselcuk.connecttointernet.network.model.MarsData
import com.eneselcuk.connecttointernet.overview.adapter.PhotoGridAdapter

@BindingAdapter("app:imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)

    }
}

@BindingAdapter("app:listAdapter")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsData>?) {
    val RcData = recyclerView.adapter as PhotoGridAdapter
    RcData.submitList(data?.take(20))
}

@BindingAdapter("app:marsApiStatus")
fun ImageView.bindStatus(status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.DONE -> {
            this.visibility = View.GONE
        }
        MarsApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
    }
}