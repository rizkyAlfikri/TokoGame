package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    val platform: PlatformXX?,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: RequirementsEn?,
    @SerializedName("requirements_ru")
    val requirementsRu: Any?
)