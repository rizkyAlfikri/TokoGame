package com.alfikri.rizky.domain.usecase

import com.alfikri.rizky.domain.model.GameFavorite
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteUseCae, v 0.1 10/6/2020 9:03 PM by Rizky Alfikri Rachmat
 */
interface GameFavoriteUseCae {

    fun getAllFavoriteGames(): Flow<List<GameFavorite>>

    suspend fun insertGameFavorite(gameFavorite: GameFavorite)

    suspend fun deleteGameFavorite(idGame: Int)
}