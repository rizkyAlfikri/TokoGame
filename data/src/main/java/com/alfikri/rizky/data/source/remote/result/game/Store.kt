package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("store")
    val store: StoreX?,
    @SerializedName("url_en")
    val urlEn: String?,
    @SerializedName("url_ru")
    val urlRu: Any?
)