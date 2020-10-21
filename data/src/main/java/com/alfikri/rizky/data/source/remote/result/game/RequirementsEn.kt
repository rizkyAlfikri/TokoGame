package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class RequirementsEn(
    @SerializedName("minimum")
    val minimum: String?,
    @SerializedName("recommended")
    val recommended: String?
)