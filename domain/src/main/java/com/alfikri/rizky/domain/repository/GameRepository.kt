package com.alfikri.rizky.domain.repository

import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.*
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameRepository, v 0.1 9/21/2020 12:13 PM by Rizky Alfikri Rachmat
 */
interface GameRepository {

    fun getGameStore(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>>

    fun getGamePromo(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>>

    fun getGameDetail(idGame: Int): Flow<Resource<GameDetail>>

    fun getGameAchievment(idGame: Int): Flow<Resource<List<GameAchievment>>>

    fun getGameScreenshot(idGame: Int): Flow<Resource<List<GameScreenshot>>>

    fun getGameVideo(idGame: Int): Flow<Resource<List<GameVideo>>>

    fun getGameSimilarVisualy(idGame: Int): Flow<Resource<List<Game>>>

    fun getAllFavoriteGames(): Flow<List<GameFavorite>>

    fun getFavoriteGameById(idGame: Int): Flow<Game?>

    suspend fun insertGameFavorite(gameFavorite: GameFavorite)

    suspend fun deleteGameFavorite(idGame: Int)

    fun getAllCartGame(): Flow<List<GameCart>>

    suspend fun insertGameCart(gameCart: GameCart)

    suspend fun deleteGameCart(idGame: Int)

    suspend fun deleteAllGameCarts(gamesCarts: List<GameCart>)

    suspend fun updateCheckedGameCart(gameCart: GameCart)
}