package com.alfikri.rizky.data.source.remote.result.gameachievment


import com.google.gson.annotations.SerializedName

data class GameAchievmentResult(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val gameAchievmentDataResults: List<GameAchievmentDataResult>?
)