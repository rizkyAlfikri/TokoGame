package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.model.GameFavorite
import com.alfikri.rizky.domain.repository.GameRepository
import com.alfikri.rizky.domain.usecase.GameFavoriteUseCae
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteUseCase, v 0.1 10/6/2020 9:04 PM by Rizky Alfikri Rachmat
 */
class GameFavoriteInteractor (private val gameRepository: GameRepository): GameFavoriteUseCae {
    override fun getAllFavoriteGames(): Flow<List<GameFavorite>> {
        return gameRepository.getAllFavoriteGames()
    }

    override suspend fun insertGameFavorite(gameFavorite: GameFavorite) {
        gameRepository.insertGameFavorite(gameFavorite)
    }

    override suspend fun deleteGameFavorite(idGame: Int) {
        gameRepository.deleteGameFavorite(idGame)
    }
}