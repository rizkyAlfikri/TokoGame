package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class Clip(
    @SerializedName("clip")
    val clip: String?,
    @SerializedName("clips")
    val clips: Clips?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("video")
    val video: String?
)