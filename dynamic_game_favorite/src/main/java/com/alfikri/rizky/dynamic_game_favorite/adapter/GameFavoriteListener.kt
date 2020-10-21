package com.alfikri.rizky.dynamic_game_favorite.adapter

import com.alfikri.rizky.tokogame.model.GameFavoriteModel


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteListener, v 0.1 10/8/2020 7:26 PM by Rizky Alfikri Rachmat
 */
interface GameFavoriteListener {
    fun onClickGameFavorite(idGame: Int)

    fun onClickDeleteFavorite(gameFavoriteModel: GameFavoriteModel)
}