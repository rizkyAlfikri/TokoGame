package com.alfikri.rizky.tokogame.model


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameDetailModel, v 0.1 9/29/2020 6:33 AM by Rizky Alfikri Rachmat
 */
data class GameDetailModel (
    val gameId: Int,
    val name: String,
    val genre: String,
    val platform: String,
    val date: String,
    val rating: Double,
    val description: String,
    val specificPlatform: String,
    val metacritic: String,
    val developer: String,
    val publisher: String,
    val playtime: String,
    val ageRating: String,
    val tags: String,
    val reviewCount: Int,
    val rattingScore: Map<String, Int>,
    val imagePath: String
)