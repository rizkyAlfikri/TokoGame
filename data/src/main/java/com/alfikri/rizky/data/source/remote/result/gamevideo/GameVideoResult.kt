package com.alfikri.rizky.data.source.remote.result.gamevideo


import com.google.gson.annotations.SerializedName

data class GameVideoResult(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val gameVideosDataResults: List<GameVideosDataResult>?
)