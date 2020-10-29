package com.example.interview.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Origin(
    val name: String = "",
    val url: String = ""
) : Parcelable