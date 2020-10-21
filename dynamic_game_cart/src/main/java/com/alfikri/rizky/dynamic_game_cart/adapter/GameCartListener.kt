package com.alfikri.rizky.dynamic_game_cart.adapter

import com.alfikri.rizky.tokogame.model.GameCartModel


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartListener, v 0.1 10/10/2020 1:12 PM by Rizky Alfikri Rachmat
 */
interface GameCartListener {
    fun updateCheckedGame(gameCartModel: GameCartModel)

    fun deleteCartGame(gameCartModel: GameCartModel)

    fun toDetailGameActivity(idGame: Int)
}