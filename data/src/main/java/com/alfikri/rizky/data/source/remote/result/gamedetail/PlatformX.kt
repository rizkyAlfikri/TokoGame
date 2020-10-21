package com.alfikri.rizky.data.source.remote.result.gamedetail


import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    val platform: PlatformXX?,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements")
    val requirements: Requirements?
)