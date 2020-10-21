package com.alfikri.rizky.data.mapper

import com.alfikri.rizky.data.source.remote.result.game.GameDataResult
import com.alfikri.rizky.data.source.remote.result.game.Genre
import com.alfikri.rizky.data.source.remote.result.game.ParentPlatform
import com.alfikri.rizky.data.source.remote.result.gameachievment.GameAchievmentDataResult
import com.alfikri.rizky.data.source.remote.result.gamedetail.*
import com.alfikri.rizky.data.source.remote.result.gamescreenshot.GameScreenshotDataResult
import com.alfikri.rizky.data.source.remote.result.gamevideo.GameVideosDataResult
import com.alfikri.rizky.domain.model.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameResultMapper, v 0.1 9/21/2020 9:15 PM by Rizky Alfikri Rachmat
 */
object GameResultMapper {
    fun transformFromGameResult(gameResults: List<GameDataResult>): List<Game> {
        return gameResults.map(::transformFromGameResult)
    }

    fun transformFromDetailResult(gameDetail: GameDetailResult): GameDetail {
        return with(gameDetail) {
            GameDetail(
                id ?: 0,
                name.toString(),
                genreDetails?.transformGenre().toString(),
                parentPlatforms?.transformParentPlatform().toString(),
                released.toString(),
                rating ?: 0.0,
                description.toString(),
                platforms?.tranformSpecificPlatform().toString(),
                metacritic?.toString().toString(),
                developers?.tranformDeveloper().toString(),
                publishers?.tranformPublisher().toString(),
                playtime.toString(),
                esrbRating?.name.toString(),
                tags?.transformTag().toString(),
                reviewsCount ?: 0,
                ratings?.transformMapRatingScore() ?: mapOf(),
                backgroundImage.toString()
                )
        }
    }

    fun transformFromAchievmentResult(gameAchievment: List<GameAchievmentDataResult>): List<GameAchievment> {
        return gameAchievment.map { game ->
            GameAchievment(
                game.id ?: 0,
                game.name.toString(),
                game.description.toString(),
                game.percent.toString(),
                game.image.toString()
            )
        }
    }

    fun transformFromScreenshotResult(gameScreenshot: List<GameScreenshotDataResult>): List<GameScreenshot> {
        return gameScreenshot.map { game ->
            GameScreenshot(
                game.image.toString(),
                game.height ?: 0,
                game.width ?: 0
            )
        }
    }

    fun transformFromVideoResult(gameVideo: List<GameVideosDataResult>): List<GameVideo> {
        return gameVideo.map { game ->
            GameVideo(
                game.thumbnails?.maxresdefault?.url.toString(),
                game.externalId.toString()
            )
        }
    }

    private fun transformFromGameResult(gameDataResults: GameDataResult): Game {
        return Game(
            gameId = gameDataResults.id ?: 0,
            name = gameDataResults.name ?: "",
            genre = gameDataResults.genres.transformGenre(),
            rating = gameDataResults.rating ?: 0.0,
            platform = gameDataResults.parentPlatforms.transformParentPlatform(),
            backgroundImage = gameDataResults.backgroundImage ?: ""
        )
    }

    private fun List<Genre>?.transformGenre(): String {
        val genreResult = StringBuilder()
        this?.forEach { genre ->
            genreResult.append(genre.name).append(", ")
        }

        return genreResult.toString().dropLast(2)
    }

    private fun List<ParentPlatform>?.transformParentPlatform(): String {
        val platformResult = StringBuilder()
        this?.forEach { platform ->
            platformResult.append(platform.platform?.name).append(", ")
        }

        return platformResult.toString().dropLast(2)
    }

    private fun List<PlatformX>?.tranformSpecificPlatform(): String {
        val platformBuilder = StringBuilder()
        this?.forEach { platform ->
            platformBuilder.append(platform.platform?.name).append(", ")
        }

        return platformBuilder.toString().dropLast(2)
    }

    private fun List<Developer>?.tranformDeveloper(): String {
        val developerBuilder = StringBuilder()
        this?.forEach { developer ->
            developerBuilder.append(developer.name).append(", ")
        }

        return developerBuilder.toString().dropLast(2)
    }

    private fun List<Publisher>?.tranformPublisher(): String {
        val publisherBuilder = StringBuilder()
        this?.forEach { publisher ->
            publisherBuilder.append(publisher.name).append(", ")
        }

        return publisherBuilder.toString().dropLast(2)
    }

    private fun List<Tag>?.transformTag(): String {
        val tagBuilder = StringBuilder()
        this?.forEach { tag ->
            tagBuilder.append(tag.name).append(", ")
        }

        return tagBuilder.toString().dropLast(2)
    }

    private fun List<Rating>?.transformMapRatingScore(): MutableMap<String, Int> {
        val ratingMap = mutableMapOf<String, Int>()
        this?.forEach {rating ->
            when (rating.title) {
                RECOMENDED -> ratingMap[RECOMENDED] = rating.count ?: 0
                EXCEPTIONAL -> ratingMap[EXCEPTIONAL] = rating.count ?: 0
                MEH -> ratingMap[MEH] = rating.count ?: 0
                SKIP -> ratingMap[SKIP] = rating.count ?: 0
            }
        }

        return ratingMap
    }

    private const val RECOMENDED = "recommended"
    private const val EXCEPTIONAL = "exceptional"
    private const val MEH = "meh"
    private const val SKIP = "skip"
}