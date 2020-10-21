package com.alfikri.rizky.data.mapper

import com.alfikri.rizky.data.source.local.entity.GameCartEntity
import com.alfikri.rizky.data.source.local.entity.GameFavoriteEntity
import com.alfikri.rizky.domain.model.Game
import com.alfikri.rizky.domain.model.GameCart
import com.alfikri.rizky.domain.model.GameFavorite

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameEntityMapper, v 0.1 9/21/2020 9:02 PM by Rizky Alfikri Rachmat
 */
object GameEntityMapper {

    fun transfromFromFavoriteEntity(gameFavorites: List<GameFavoriteEntity>): List<GameFavorite> {
        return gameFavorites.map(::transfromFromFavoriteEntity)
    }

    fun transformFavorite(gameFavorites: GameFavoriteEntity?): Game? {
        return gameFavorites?.let { game ->
            Game(
                game.gameId,
                game.name,
                game.genre,
                game.price,
                game.rating,
                game.platform,
                game.backgroundImage
            )
        }
    }

    private fun transfromFromFavoriteEntity(gameFavorites: GameFavoriteEntity): GameFavorite {
        return with(gameFavorites) {
            GameFavorite(
                gameId,
                name,
                genre,
                price,
                rating,
                platform,
                backgroundImage,
                time
            )
        }
    }

    fun transformFromFavorite(gameFavorite: GameFavorite): GameFavoriteEntity {
        return with(gameFavorite) {
            GameFavoriteEntity(
                gameId = gameId,
                name = name,
                genre = genre,
                price = price,
                rating = rating,
                platform = platform,
                backgroundImage = backgroundImage,
                time = time
            )
        }
    }

    fun transfromFromCartEntity(gameCarts: List<GameCartEntity>): List<GameCart> {
        return gameCarts.map(::transfromFromCartEntity)
    }

    private fun transfromFromCartEntity(gameCart: GameCartEntity): GameCart {
        return with(gameCart) {
            GameCart(
                gameId,
                name,
                genre,
                price,
                rating,
                platform,
                backgroundImage,
                time,
                isChecked
            )
        }
    }

    fun transformFromCart(games: List<GameCart>): List<GameCartEntity> {
        return games.map(this::transformFromCart)
    }

    fun transformFromCart(game: GameCart): GameCartEntity {
        return with(game) {
            GameCartEntity(
                gameId = gameId,
                name = name,
                genre = genre,
                price = price,
                rating = rating,
                platform = platform,
                backgroundImage = backgroundImage,
                time = time,
                isChecked = isChecked
            )
        }
    }
}