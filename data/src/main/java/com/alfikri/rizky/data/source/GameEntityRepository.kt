package com.alfikri.rizky.data.source

import androidx.lifecycle.MutableLiveData
import com.alfikri.rizky.data.NetworkBoundResource
import com.alfikri.rizky.data.mapper.GameEntityMapper
import com.alfikri.rizky.data.mapper.GameResultMapper
import com.alfikri.rizky.data.source.local.LocalEntityDataSource
import com.alfikri.rizky.data.source.remote.RemoteEntityDataSource
import com.alfikri.rizky.data.source.remote.network.ApiResponse
import com.alfikri.rizky.data.source.remote.result.game.GameDataResult
import com.alfikri.rizky.data.source.remote.result.gameachievment.GameAchievmentDataResult
import com.alfikri.rizky.data.source.remote.result.gamedetail.GameDetailResult
import com.alfikri.rizky.data.source.remote.result.gamescreenshot.GameScreenshotDataResult
import com.alfikri.rizky.data.source.remote.result.gamevideo.GameVideosDataResult
import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.*
import com.alfikri.rizky.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameEntityRepository, v 0.1 9/21/2020 8:09 PM by Rizky Alfikri Rachmat
 */
class GameEntityRepository(
    private val local: LocalEntityDataSource,
    private val remote: RemoteEntityDataSource
) : GameRepository {

    override fun getGameStore(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameDataResult>>() {

            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<Game>> {
                return flow { MutableLiveData<Game>() }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameDataResult>>> {
                return remote.getGameList(query)
            }

            override suspend fun fetchDataFromCall(data: List<GameDataResult>): List<Game> {
                return GameResultMapper.transformFromGameResult(data)
            }

            override suspend fun saveCallResult(data: List<GameDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGamePromo(
        query: Map<String, String>
    ): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameDataResult>>() {
            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<Game>> {
                return flow { MutableLiveData<Game>() }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameDataResult>>> {
                return remote.getGameList(query)
            }

            override suspend fun fetchDataFromCall(data: List<GameDataResult>): List<Game> {
                return GameResultMapper.transformFromGameResult(data)
            }

            override suspend fun saveCallResult(data: List<GameDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGameDetail(idGame: Int): Flow<Resource<GameDetail>> {
        return object : NetworkBoundResource<GameDetail, GameDetailResult>() {
            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<GameDetail> {
                return flow { MutableLiveData<GameDetail>() }
            }

            override fun shouldFetch(data: GameDetail?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResult>> {
                return remote.getGameDetail(idGame)
            }

            override suspend fun fetchDataFromCall(data: GameDetailResult): GameDetail {
                return GameResultMapper.transformFromDetailResult(data)
            }

            override suspend fun saveCallResult(data: GameDetailResult) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGameAchievment(idGame: Int): Flow<Resource<List<GameAchievment>>> {
        return object :
            NetworkBoundResource<List<GameAchievment>, List<GameAchievmentDataResult>>() {
            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<GameAchievment>> {
                return flow { MutableLiveData<List<GameAchievment>>() }
            }

            override fun shouldFetch(data: List<GameAchievment>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameAchievmentDataResult>>> {
                return remote.getGameAchievment(idGame)
            }

            override suspend fun fetchDataFromCall(data: List<GameAchievmentDataResult>): List<GameAchievment> {
                return GameResultMapper.transformFromAchievmentResult(data)
            }

            override suspend fun saveCallResult(data: List<GameAchievmentDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGameScreenshot(idGame: Int): Flow<Resource<List<GameScreenshot>>> {
        return object :
            NetworkBoundResource<List<GameScreenshot>, List<GameScreenshotDataResult>>() {
            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<GameScreenshot>> {
                return flow { MutableLiveData<List<GameScreenshot>>() }
            }

            override fun shouldFetch(data: List<GameScreenshot>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameScreenshotDataResult>>> {
                return remote.getGameScreenshot(idGame)
            }

            override suspend fun fetchDataFromCall(data: List<GameScreenshotDataResult>): List<GameScreenshot> {
                return GameResultMapper.transformFromScreenshotResult(data)
            }

            override suspend fun saveCallResult(data: List<GameScreenshotDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGameVideo(idGame: Int): Flow<Resource<List<GameVideo>>> {
        return object : NetworkBoundResource<List<GameVideo>, List<GameVideosDataResult>>() {
            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<GameVideo>> {
                return flow { MutableLiveData<List<GameVideo>>() }
            }

            override fun shouldFetch(data: List<GameVideo>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameVideosDataResult>>> {
                return remote.getGameVideo(idGame)
            }

            override suspend fun fetchDataFromCall(data: List<GameVideosDataResult>): List<GameVideo> {
                return GameResultMapper.transformFromVideoResult(data)
            }

            override suspend fun saveCallResult(data: List<GameVideosDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getGameSimilarVisualy(idGame: Int): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameDataResult>>() {

            override fun needDataFromDB(): Boolean {
                return false
            }

            override fun loadFromDB(): Flow<List<Game>> {
                return flow { MutableLiveData<List<Game>>() }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameDataResult>>> {
                return remote.getGameSimilarVisualy(idGame)
            }

            override suspend fun fetchDataFromCall(data: List<GameDataResult>): List<Game> {
                return GameResultMapper.transformFromGameResult(data)
            }

            override suspend fun saveCallResult(data: List<GameDataResult>) {
                // no operation
            }
        }.asFlow()
    }

    override fun getAllFavoriteGames(): Flow<List<GameFavorite>> {
        return local.getAllFavoriteGames()
            .map { game -> GameEntityMapper.transfromFromFavoriteEntity(game) }
    }

    override fun getFavoriteGameById(idGame: Int): Flow<Game?> {
        return local.getFavoriteGameById(idGame)
            .map { game -> GameEntityMapper.transformFavorite(game) }
    }

    override suspend fun insertGameFavorite(gameFavorite: GameFavorite) {
        val gameFavoriteEntity = GameEntityMapper.transformFromFavorite(gameFavorite )
        return local.insertGameFavorite(gameFavoriteEntity)
    }

    override suspend fun deleteGameFavorite(idGame: Int) {
        local.deleteGameFavorite(idGame)
    }

    override fun getAllCartGame(): Flow<List<GameCart>> {
        return local.getAllCartGame().map { game -> GameEntityMapper.transfromFromCartEntity(game) }
    }

    override suspend fun insertGameCart(gameCart: GameCart) {
        val gameCartEntity = GameEntityMapper.transformFromCart(gameCart)
        local.insertGameCart(gameCartEntity)
    }

    override suspend fun deleteGameCart(idGame: Int) {
        return local.deleteGameCart(idGame)
    }

    override suspend fun deleteAllGameCarts(gamesCarts: List<GameCart>) {
        val gameCartEntities = GameEntityMapper.transformFromCart(gamesCarts)
        local.deleteAllGameCarts(gameCartEntities)
    }

    override suspend fun updateCheckedGameCart(gameCart: GameCart) {
        val gameCartEntity = GameEntityMapper.transformFromCart(gameCart)
        local.updateCheckedGame(gameCartEntity)
    }
}