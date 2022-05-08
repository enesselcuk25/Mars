package com.eneselcuk.connecttointernet.network.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class MarsData(
    val id: String,
    val type: String,
    val price: Double,
    val img_src: String,
) : Parcelable
