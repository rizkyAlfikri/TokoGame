package com.alfikri.rizky.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteEntity, v 0.1 10/1/2020 9:05 PM by Rizky Alfikri Rachmat
 */
@Entity(tableName = "game_favorite_entity")
data class GameFavoriteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "game_id")
    val gameId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "price")
    val price: Int = 0,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "platform")
    val platform: String,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "time")
    val time: Long,
)