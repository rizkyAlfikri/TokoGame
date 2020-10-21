package com.alfikri.rizky.data.source.local.room

import androidx.room.*
import com.alfikri.rizky.data.source.local.entity.GameCartEntity
import com.alfikri.rizky.data.source.local.entity.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version TourismDao, v 0.1 9/21/2020 8:54 PM by Rizky Alfikri Rachmat
 */
@Dao
interface GameDao {

    @Query("SELECT * FROM game_favorite_entity ORDER BY id DESC")
    fun getAllFavoriteGames(): Flow<List<GameFavoriteEntity>>

    @Query("SELECT * FROM game_favorite_entity WHERE game_id = :idGame")
    fun getFavoriteGameById(idGame: Int): Flow<GameFavoriteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameFavorite(gameFavoriteEntity: GameFavoriteEntity)

    @Query("DELETE FROM game_favorite_entity WHERE game_id = :idGame")
    suspend fun deleteGameFavorite(idGame: Int)

    @Query("SELECT * FROM game_cart_entity ORDER BY time DESC")
    fun getAllCartGame(): Flow<List<GameCartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameCart(gameCartEntity: GameCartEntity)

    @Query("DELETE FROM game_cart_entity WHERE game_id = :idGame")
    suspend fun deleteGameCart(idGame: Int)

    @Delete
    suspend fun deleteAllGameCarts(gamesCartEntities: List<GameCartEntity>)

    @Query("UPDATE game_cart_entity SET is_checked = :isChecked WHERE game_id = :idGame")
    suspend fun updateCheckedGame(idGame: Int, isChecked: Boolean)
}