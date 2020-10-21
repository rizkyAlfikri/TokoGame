package com.alfikri.rizky.data.source.remote.result.gamevideo


import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: Default?,
    @SerializedName("high")
    val high: High?,
    @SerializedName("maxresdefault")
    val maxresdefault: Maxresdefault?,
    @SerializedName("medium")
    val medium: Medium?,
    @SerializedName("sddefault")
    val sddefault: Sddefault?
)