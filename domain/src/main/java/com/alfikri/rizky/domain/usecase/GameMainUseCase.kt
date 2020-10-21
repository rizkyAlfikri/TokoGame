package com.alfikri.rizky.domain.usecase

import com.alfikri.rizky.domain.model.GameCart
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameMainUseCase, v 0.1 10/6/2020 8:21 PM by Rizky Alfikri Rachmat
 */
interface GameMainUseCase {
    fun getAllCartGame(): Flow<List<GameCart>>
}