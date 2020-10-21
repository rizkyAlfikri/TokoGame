package com.alfikri.rizky.domain.usecase

import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.Game
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameUsecase, v 0.1 9/21/2020 10:22 PM by Rizky Alfikri Rachmat
 */
interface GameUsecase {

    fun getGameStore(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>>

    fun getGamePromo(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>>

}