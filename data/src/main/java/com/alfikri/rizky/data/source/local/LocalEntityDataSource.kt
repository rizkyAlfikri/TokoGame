package com.alfikri.rizky.data.source.local

import com.alfikri.rizky.data.source.local.entity.GameCartEntity
import com.alfikri.rizky.data.source.local.entity.GameFavoriteEntity
import com.alfikri.rizky.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version LocalEntityDataSource, v 0.1 9/21/2020 8:48 PM by Rizky Alfikri Rachmat
 */
class LocalEntityDataSource(private val gameDao: GameDao) {

    fun getAllFavoriteGames(): Flow<List<GameFavoriteEntity>> {
        return  gameDao.getAllFavoriteGames()
    }

    fun getFavoriteGameById(idGame: Int): Flow<GameFavoriteEntity?> {
        return gameDao.getFavoriteGameById(idGame)
    }

    suspend fun insertGameFavorite(gameFavoriteEntity: GameFavoriteEntity) {
        gameDao.insertGameFavorite(gameFavoriteEntity)
    }

    suspend fun deleteGameFavorite(idGame: Int) {
        gameDao.deleteGameFavorite(idGame)
    }

    fun getAllCartGame(): Flow<List<GameCartEntity>> {
        return gameDao.getAllCartGame()
    }

    suspend fun insertGameCart(gameCartEntity: GameCartEntity) {
        gameDao.insertGameCart(gameCartEntity)
    }

    suspend fun deleteGameCart(idGame: Int) {
        gameDao.deleteGameCart(idGame)
    }

    suspend fun updateCheckedGame(gameCartEntity: GameCartEntity) {
        gameDao.updateCheckedGame(gameCartEntity.gameId, gameCartEntity.isChecked)
    }

    suspend fun deleteAllGameCarts(gamesCartEntities: List<GameCartEntity>) {
        gameDao.deleteAllGameCarts(gamesCartEntities)
    }
}