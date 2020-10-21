package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.model.GameCart
import com.alfikri.rizky.domain.repository.GameRepository
import com.alfikri.rizky.domain.usecase.GameMainUseCase
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameMainInteractor, v 0.1 10/6/2020 8:19 PM by Rizky Alfikri Rachmat
 */
class GameMainInteractor(private val gameRepository: GameRepository): GameMainUseCase {
    override fun getAllCartGame(): Flow<List<GameCart>> {
        return gameRepository.getAllCartGame()
    }
}