package com.alfikri.rizky.tokogame.mapper

import com.alfikri.rizky.domain.model.*
import com.alfikri.rizky.tokogame.model.*
import java.util.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameModelMapper, v 0.1 9/29/2020 6:35 AM by Rizky Alfikri Rachmat
 */
object GameModelMapper {

    fun transfromFromGame(games: List<Game>?): List<GameModel>? {
        return games?.map(::transfromFromGame)
    }

    fun transformFromDetail(gameDetail: GameDetail): GameDetailModel {
        return with(gameDetail) {
            GameDetailModel(
                gameId,
                name,
                genre,
                platform,
                date,
                rating,
                description,
                specificPlatform,
                metacritic,
                developer,
                publisher,
                playtime,
                ageRating,
                tags,
                reviewCount,
                rattingScore,
                imagePath
            )
        }
    }

    fun transformFromDetail(gameDetail: GameDetailModel): Game {
        return with(gameDetail) {
            Game(
                gameId = gameId,
                name = name,
                genre = genre,
                rating = rating,
                platform = platform,
                backgroundImage = imagePath
            )
        }
    }

    fun transformFromAchievments(gameAchievments: List<GameAchievment>?): List<GameAchievmentModel>? {
        return gameAchievments?.map(::transformFromAchievments)
    }

    fun transformFromScreenshot(gameScreenshots: List<GameScreenshot>?): List<GameScreenshotModel>? {
        return gameScreenshots?.map(::transformFromScreenshot)
    }

    fun transformFromVideo(gameVideos: List<GameVideo>?): List<GameVideoModel>? {
        return gameVideos?.map(::transformFromVideo)
    }

    fun transformToFavorite(game: Game): GameFavorite {
        val time = Calendar.getInstance().time.time
        return with(game) {
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

    fun transformFromFavorite(gameFavorite: List<GameFavorite>): List<GameFavoriteModel> {
        return gameFavorite.map(this::transformFromFavorite)
    }

   private fun transformFromFavorite(gameFavorite: GameFavorite): GameFavoriteModel {
        return with(gameFavorite) {
            GameFavoriteModel(
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

    fun transformFromFavoriteModel(gameFavorite: GameFavoriteModel): GameFavorite {
        return with(gameFavorite) {
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

    fun transformFromCartModel(gameCartModels: List<GameCartModel>): List<GameCart> {
        return gameCartModels.map(this::transformFromCartModel)
    }

    fun transformCartFromGame(game: Game): GameCart {
        val time = Calendar.getInstance().time.time
        return with(game) {
            GameCart(
                gameId,
                name,
                genre,
                50,
                rating,
                platform,
                backgroundImage,
                time,
                false
            )
        }
    }

    fun transformFromCartModel(gameCartModel: GameCartModel): GameCart {
        return with(gameCartModel) {
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

    fun transformFromCart(gameCarts: List<GameCart>): List<GameCartModel> {
        return gameCarts.map(this::transformFromCart)
    }

    private fun transformFromCart(gameCart: GameCart): GameCartModel {
        return with(gameCart) {
            GameCartModel(
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

    private fun transfromFromGame(game: Game): GameModel {
        return with(game) {
            GameModel(
                gameId,
                name,
                genre,
                price,
                rating,
                platform,
                backgroundImage
            )
        }
    }

    private fun transformFromAchievments(gameAchievment: GameAchievment): GameAchievmentModel {
        return with(gameAchievment) {
            GameAchievmentModel(
                id,
                name,
                description,
                percent,
                imagePath
            )
        }
    }

    private fun transformFromScreenshot(gameScreenshot: GameScreenshot): GameScreenshotModel {
        return with(gameScreenshot) {
            GameScreenshotModel(
                imagePath,
                height,
                width
            )
        }
    }

    private fun transformFromVideo(gameVideo: GameVideo): GameVideoModel {
        return with(gameVideo) {
            GameVideoModel(
                imagePath,
                externalUrl
            )
        }
    }
}