package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.Game
import com.alfikri.rizky.domain.repository.GameRepository
import com.alfikri.rizky.domain.usecase.GameUsecase
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameInteractor, v 0.1 9/21/2020 10:22 PM by Rizky Alfikri Rachmat
 */
class GameInteractor(private val gameRepository: GameRepository) :
    GameUsecase {

    override fun getGameStore(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>> {
        return gameRepository.getGameStore(query)
    }

    override fun getGamePromo(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>> {
        return gameRepository.getGameStore(query)
    }
}