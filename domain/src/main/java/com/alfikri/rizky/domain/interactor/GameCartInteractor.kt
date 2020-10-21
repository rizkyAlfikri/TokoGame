package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.model.GameCart
import com.alfikri.rizky.domain.repository.GameRepository
import com.alfikri.rizky.domain.usecase.GameCartUseCase
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartInteractor, v 0.1 10/9/2020 9:17 PM by Rizky Alfikri Rachmat
 */
class GameCartInteractor(private val gameRepository: GameRepository): GameCartUseCase {
    override fun getAllCartGame(): Flow<List<GameCart>> {
        return gameRepository.getAllCartGame()
    }

    override suspend fun insertGameCart(gameCart: GameCart) {
        gameRepository.insertGameCart(gameCart)
    }

    override suspend fun deleteGameCart(idGame: Int) {
        gameRepository.deleteGameCart(idGame)
    }

    override suspend fun deleteAllGameCarts(gamesCarts: List<GameCart>) {
        gameRepository.deleteAllGameCarts(gamesCarts)
    }

    override suspend fun updateCheckedGameCart(gameCart: GameCart) {
        gameRepository.updateCheckedGameCart(gameCart)
    }
}