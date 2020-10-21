package com.alfikri.rizky.data.source.remote.result.gameachievment


import com.google.gson.annotations.SerializedName

data class GameAchievmentDataResult(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("percent")
    val percent: String?
)