package com.alfikri.rizky.domain.usecase

import com.alfikri.rizky.domain.model.GameCart
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartUseCase, v 0.1 10/9/2020 9:16 PM by Rizky Alfikri Rachmat
 */
interface GameCartUseCase {

    fun getAllCartGame(): Flow<List<GameCart>>

    suspend fun insertGameCart(gameCart: GameCart)

    suspend fun deleteGameCart(idGame: Int)

    suspend fun deleteAllGameCarts(gamesCarts: List<GameCart>)

    suspend fun updateCheckedGameCart(gameCart: GameCart)
}