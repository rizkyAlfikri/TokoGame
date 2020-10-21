package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.*
import com.alfikri.rizky.domain.repository.GameRepository
import com.alfikri.rizky.domain.usecase.GameDetailUsecase
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameDetailInteractor, v 0.1 9/28/2020 7:40 PM by Rizky Alfikri Rachmat
 */
class GameDetailInteractor(private val gameRepository: GameRepository):
    GameDetailUsecase {
    override fun getGameDetail(idGame: Int): Flow<Resource<GameDetail>> {
        return gameRepository.getGameDetail(idGame)
    }

    override fun getGameAchievment(idGame: Int): Flow<Resource<List<GameAchievment>>> {
        return gameRepository.getGameAchievment(idGame)
    }

    override fun getGameScreenshot(idGame: Int): Flow<Resource<List<GameScreenshot>>> {
        return gameRepository.getGameScreenshot(idGame)
    }

    override fun getGameVideo(idGame: Int): Flow<Resource<List<GameVideo>>> {
        return gameRepository.getGameVideo(idGame)
    }

    override fun getGameSimilarVisualy(idGame: Int): Flow<Resource<List<Game>>> {
        return gameRepository.getGameSimilarVisualy(idGame)
    }

    override fun getFavoriteGameById(idGame: Int): Flow<Game?> {
        return gameRepository.getFavoriteGameById(idGame)
    }

    override suspend fun insertGameFavorite(gameFavorite: GameFavorite) {
        gameRepository.insertGameFavorite(gameFavorite)
    }

    override suspend fun deleteGameFavorite(idGame: Int) {
        gameRepository.deleteGameFavorite(idGame)
    }

    override suspend fun insertGameCart(gameCart: GameCart) {
        gameRepository.insertGameCart(gameCart)
    }
}