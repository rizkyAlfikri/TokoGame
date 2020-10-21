package com.alfikri.rizky.tokogame.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartModel, v 0.1 10/9/2020 9:07 PM by Rizky Alfikri Rachmat
 */
data class GameCartModel(
    val gameId: Int,

    val name: String,

    val genre: String,

    val price: Int,

    val rating: Double,

    val platform: String,

    val backgroundImage: String,

    val time: Long,

    var isChecked: Boolean
)