package com.alfikri.rizky.domain.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavorite, v 0.1 10/10/2020 8:16 PM by Rizky Alfikri Rachmat
 */
data class GameFavorite(
    val gameId: Int,

    val name: String,

    val genre: String,

    val price: Int,

    val rating: Double,

    val platform: String,

    val backgroundImage: String,

    var time: Long,
)
