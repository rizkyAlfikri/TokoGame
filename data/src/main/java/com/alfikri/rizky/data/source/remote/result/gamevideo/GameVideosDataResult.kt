package com.alfikri.rizky.data.source.remote.result.gamevideo


import com.google.gson.annotations.SerializedName

data class GameVideosDataResult(
    @SerializedName("channel_id")
    val channelId: String?,
    @SerializedName("channel_title")
    val channelTitle: String?,
    @SerializedName("comments_count")
    val commentsCount: Int?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("dislike_count")
    val dislikeCount: Int?,
    @SerializedName("external_id")
    val externalId: String?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("like_count")
    val likeCount: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails?,
    @SerializedName("view_count")
    val viewCount: Int?
)