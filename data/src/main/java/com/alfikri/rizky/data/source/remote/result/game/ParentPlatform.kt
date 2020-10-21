package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: Platform?
)