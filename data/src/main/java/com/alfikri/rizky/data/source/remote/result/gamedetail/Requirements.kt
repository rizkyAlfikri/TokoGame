package com.alfikri.rizky.data.source.remote.result.gamedetail


import com.google.gson.annotations.SerializedName

data class Requirements(
    @SerializedName("minimum")
    val minimum: String?,
    @SerializedName("recommended")
    val recommended: String?
)