package com.alfikri.rizky.data.source.remote

import com.alfikri.rizky.data.source.remote.network.ApiResponse
import com.alfikri.rizky.data.source.remote.network.ApiService
import com.alfikri.rizky.data.source.remote.result.game.GameDataResult
import com.alfikri.rizky.data.source.remote.result.gameachievment.GameAchievmentDataResult
import com.alfikri.rizky.data.source.remote.result.gamedetail.GameDetailResult
import com.alfikri.rizky.data.source.remote.result.gamescreenshot.GameScreenshotDataResult
import com.alfikri.rizky.data.source.remote.result.gamevideo.GameVideosDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version RemoteEntityDataSource, v 0.1 9/21/2020 12:45 PM by Rizky Alfikri Rachmat
 */
class RemoteEntityDataSource(private val apiService: ApiService) {

    suspend fun getGameList(query: Map<String, String>): Flow<ApiResponse<List<GameDataResult>>> {
        return flow {
            try {
                val pages = query["page"]?.toInt() ?: 1
                query.toMutableMap().remove("page")
                val response = apiService.getGameList(query, pages)
                val gameDataResult = response.gameDataResults

                gameDataResult?.let { games ->
                    if (games.isNotEmpty()) {
                        emit(ApiResponse.Success(games))
                    } else {
                        emit(ApiResponse.Empty("Data Not Found"))
                    }
                } ?: emit(ApiResponse.Empty("Data Not Found"))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetail(idGame: Int): Flow<ApiResponse<GameDetailResult>> {
        return flow {
            try {
                val response = apiService.getGameDetail(idGame)

                response?.let { gameDetail ->
                    emit(ApiResponse.Success(gameDetail))
                } ?: emit(ApiResponse.Empty("Data not found"))

            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }
            .flowOn(Dispatchers.IO)
    }

    suspend fun getGameAchievment(idGame: Int): Flow<ApiResponse<List<GameAchievmentDataResult>>> {
        return flow {
            try {
                val response = apiService.getGameAchievment(idGame)
                response.gameAchievmentDataResults?.let { gameAchievment ->
                    if (gameAchievment.isNotEmpty()) {
                        emit(ApiResponse.Success(gameAchievment))
                    } else {
                        emit(ApiResponse.Empty("Data Not Found"))
                    }
                } ?: emit(ApiResponse.Empty("Data Not Found"))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameScreenshot(idGame: Int): Flow<ApiResponse<List<GameScreenshotDataResult>>> {
        return flow {
            try {
                val response = apiService.getGameScreenshot(idGame)
                response.gameScreenshotDataResults?.let { gameScreenshot ->
                    if (gameScreenshot.isNotEmpty()) {
                        emit(ApiResponse.Success(gameScreenshot))
                    } else {
                        emit(ApiResponse.Empty("Data not found"))
                    }
                } ?: emit(ApiResponse.Empty("Data not found"))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameVideo(idGame: Int): Flow<ApiResponse<List<GameVideosDataResult>>> {
        return flow {
            try {
                val response = apiService.getGameVideo(idGame)
                response.gameVideosDataResults?.let { gameVideo ->
                    if (gameVideo.isNotEmpty()) {
                        emit(ApiResponse.Success(gameVideo))
                    } else {
                        emit(ApiResponse.Empty("Data not found"))
                    }
                } ?: emit(ApiResponse.Empty("Data not found"))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameSimilarVisualy(idGame: Int): Flow<ApiResponse<List<GameDataResult>>> {
        return flow {
            try {
                val response = apiService.getGameSimilarVisualy(idGame)
                response.gameDataResults?.let { games ->
                    if (games.isNotEmpty()) {
                        emit(ApiResponse.Success(games))
                    } else {
                        emit(ApiResponse.Empty("Data not found"))
                    }
                } ?: emit(ApiResponse.Empty("Data not found"))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}