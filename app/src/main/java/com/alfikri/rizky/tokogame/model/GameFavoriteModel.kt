package com.alfikri.rizky.tokogame.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteModel, v 0.1 10/8/2020 7:18 PM by Rizky Alfikri Rachmat
 */
class GameFavoriteModel(
    val gameId: Int,
    val name: String,
    val genre: String,
    val price: Int = 50,
    val rating: Double,
    val platform: String,
    val backgroundImage: String,
    val time: Long
)
