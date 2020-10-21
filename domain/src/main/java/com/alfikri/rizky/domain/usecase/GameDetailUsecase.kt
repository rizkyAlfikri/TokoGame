package com.alfikri.rizky.domain.usecase

import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.*
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameDetailUsecas, v 0.1 9/28/2020 7:34 PM by Rizky Alfikri Rachmat
 */
interface GameDetailUsecase {

    fun getGameDetail(idGame: Int): Flow<Resource<GameDetail>>

    fun getGameAchievment(idGame: Int): Flow<Resource<List<GameAchievment>>>

    fun getGameScreenshot(idGame: Int): Flow<Resource<List<GameScreenshot>>>

    fun getGameVideo(idGame: Int): Flow<Resource<List<GameVideo>>>

    fun getGameSimilarVisualy(idGame: Int): Flow<Resource<List<Game>>>

    fun getFavoriteGameById(idGame: Int): Flow<Game?>

    suspend fun insertGameFavorite(gameFavorite: GameFavorite)

    suspend fun deleteGameFavorite(idGame: Int)

    suspend fun insertGameCart(gameCart: GameCart)
}