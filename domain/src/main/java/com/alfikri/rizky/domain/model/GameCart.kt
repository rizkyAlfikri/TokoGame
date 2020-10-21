package com.alfikri.rizky.domain.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCart, v 0.1 10/9/2020 9:06 PM by Rizky Alfikri Rachmat
 */
data class GameCart (
    val gameId: Int,

    val name: String,

    val genre: String,

    val price: Int,

    val rating: Double,

    val platform: String,

    val backgroundImage: String,

    var time: Long,

    val isChecked: Boolean
)