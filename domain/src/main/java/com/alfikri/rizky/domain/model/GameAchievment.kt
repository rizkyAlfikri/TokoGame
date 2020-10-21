package com.alfikri.rizky.domain.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameAchievmentModel, v 0.1 9/27/2020 9:43 PM by Rizky Alfikri Rachmat
 */
data class GameAchievment (
    val id: Int,
    val name: String,
    val description: String,
    val percent: String,
    val imagePath: String
)