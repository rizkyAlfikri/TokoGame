package com.alfikri.rizky.data.source.remote.result.game


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    val years: List<Year>?
)