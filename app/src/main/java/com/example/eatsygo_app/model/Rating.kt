package com.example.eatsygo_app.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    val rate: Float = 0.0F,
    val count: Int? = null
): Parcelable