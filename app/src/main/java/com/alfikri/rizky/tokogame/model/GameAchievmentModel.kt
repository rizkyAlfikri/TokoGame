package com.alfikri.rizky.tokogame.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameAchievmentModel, v 0.1 9/29/2020 6:33 AM by Rizky Alfikri Rachmat
 */
data class GameAchievmentModel (
    val id: Int,
    val name: String,
    val description: String,
    val percent: String,
    val imagePath: String
)