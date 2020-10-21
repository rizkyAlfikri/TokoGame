package com.alfikri.rizky.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfikri.rizky.data.source.local.entity.GameCartEntity
import com.alfikri.rizky.data.source.local.entity.GameFavoriteEntity


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameDatabase, v 0.1 9/21/2020 8:57 PM by Rizky Alfikri Rachmat
 */
@Database(
    entities = [GameFavoriteEntity::class, GameCartEntity::class],
    exportSchema = false,
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}