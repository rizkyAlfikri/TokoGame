package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class Clips(
    @SerializedName("full")
    val full: String?,
    @SerializedName("320")
    val x320: String?,
    @SerializedName("640")
    val x640: String?
)