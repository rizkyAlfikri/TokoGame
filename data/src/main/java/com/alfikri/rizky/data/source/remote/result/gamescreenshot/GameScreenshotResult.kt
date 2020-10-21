package com.alfikri.rizky.data.source.remote.result.gamescreenshot


import com.google.gson.annotations.SerializedName

data class GameScreenshotResult(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: Any?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val gameScreenshotDataResults: List<GameScreenshotDataResult>?
)