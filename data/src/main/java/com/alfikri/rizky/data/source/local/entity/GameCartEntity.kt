package com.alfikri.rizky.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameAddCartEntity, v 0.1 10/1/2020 9:07 PM by Rizky Alfikri Rachmat
 */
@Entity(tableName = "game_cart_entity")
class GameCartEntity(

    @PrimaryKey(autoGenerate = false)
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

    @ColumnInfo(name = "is_checked")
    val isChecked: Boolean
)