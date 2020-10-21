package com.alfikri.rizky.data.source.remote.network

import com.alfikri.rizky.data.source.remote.result.game.GameResult
import com.alfikri.rizky.data.source.remote.result.gameachievment.GameAchievmentResult
import com.alfikri.rizky.data.source.remote.result.gamedetail.GameDetailResult
import com.alfikri.rizky.data.source.remote.result.gamescreenshot.GameScreenshotResult
import com.alfikri.rizky.data.source.remote.result.gamevideo.GameVideoResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version ApiService, v 0.1 9/21/2020 12:46 PM by Rizky Alfikri Rachmat
 */
interface ApiService {
    @GET("games")
    suspend fun getGameList(@QueryMap query: Map<String, String>, @Query("page") page: Int): GameResult

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") idGame: Int): GameDetailResult?

    @GET("games/{id}/achievements")
    suspend fun getGameAchievment(@Path("id") idGame: Int): GameAchievmentResult

    @GET("games/{game_pk}/screenshots")
    suspend fun getGameScreenshot(@Path("game_pk") idGame: Int): GameScreenshotResult

    @GET("games/{id}/youtube")
    suspend fun getGameVideo(@Path("id") idGame: Int): GameVideoResult

    @GET("games/{id}/suggested")
    suspend fun getGameSimilarVisualy(@Path("id") idGame: Int): GameResult
}