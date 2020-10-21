package com.alfikri.rizky.tokogame.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameModel, v 0.1 9/29/2020 6:32 AM by Rizky Alfikri Rachmat
 */
data class GameModel(
    val gameId: Int,
    val name: String,
    val genre: String,
    val price: Int,
    val rating: Double,
    val platform: String,
    val backgroundImage: String
)