package com.alfikri.rizky.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameStore, v 0.1 9/21/2020 12:07 PM by Rizky Alfikri Rachmat
 */
@Parcelize
data class Game(
    val gameId: Int,
    val name: String,
    val genre: String,
    val price: Int = 0,
    val rating: Double,
    val platform: String,
    val backgroundImage: String
) : Parcelable