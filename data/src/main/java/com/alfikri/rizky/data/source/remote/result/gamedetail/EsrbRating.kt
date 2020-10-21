package com.alfikri.rizky.data.source.remote.result.gamedetail

import com.google.gson.annotations.SerializedName


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version EsrbRating, v 0.1 9/27/2020 10:57 PM by Rizky Alfikri Rachmat
 */
data class EsrbRating(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?
)