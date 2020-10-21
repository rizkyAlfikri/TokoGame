package com.alfikri.rizky.tokogame.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.*
import com.alfikri.rizky.domain.usecase.GameDetailUsecase
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version DetailViewModel, v 0.1 9/28/2020 8:45 PM by Rizky Alfikri Rachmat
 */
class DetailGameViewModel(private val gameDetailUsecase: GameDetailUsecase) : ViewModel() {

    private val _gameDetail = MutableLiveData<Resource<GameDetail>>()

    private val _gameAchievment = MutableLiveData<Resource<List<GameAchievment>>>()

    private val _gameScreenshoot = MutableLiveData<Resource<List<GameScreenshot>>>()

    private val _gameVideos = MutableLiveData<Resource<List<GameVideo>>>()

    private val _gameSimilarVisualy = MutableLiveData<Resource<List<Game>>>()

    private val _gameFavorite = MutableLiveData<Game?>()

    val gameDetail: LiveData<Resource<GameDetail>>
        get() = _gameDetail

    val gameAchievment: LiveData<Resource<List<GameAchievment>>>
        get() = _gameAchievment

    val gameScreenshot: LiveData<Resource<List<GameScreenshot>>>
        get() = _gameScreenshoot

    val gameVideos: LiveData<Resource<List<GameVideo>>>
        get() = _gameVideos

    val gameSimilarVisualy: LiveData<Resource<List<Game>>>
        get() = _gameSimilarVisualy

    val gameFavorite: LiveData<Game?>
        get() = _gameFavorite

    fun setGameId(idGame: Int) {
        viewModelScope.launch {
            gameDetailUsecase.getGameDetail(idGame).collect { gameDetailData ->
                _gameDetail.value = gameDetailData
            }

            gameDetailUsecase.getGameAchievment(idGame).collect { gameAchievmentData ->
                _gameAchievment.value = gameAchievmentData
            }

            gameDetailUsecase.getGameScreenshot(idGame).collect { gameScreenshotData ->
                _gameScreenshoot.value = gameScreenshotData
            }

            gameDetailUsecase.getGameVideo(idGame).collect { gameVideosData ->
                _gameVideos.value = gameVideosData
            }

            gameDetailUsecase.getGameSimilarVisualy(idGame).collect { gameSimilarVisualyData ->
                _gameSimilarVisualy.value = gameSimilarVisualyData
            }
        }
    }

    fun checkFavoriteGameFromDb(idGame: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameDetailUsecase.getFavoriteGameById(idGame).collect{ game ->
                _gameFavorite.postValue(game)
            }
        }
    }

    fun insertFavoriteGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            val gameFavorite = GameModelMapper.transformToFavorite(game)
            gameDetailUsecase.insertGameFavorite(gameFavorite)
        }
    }

    fun insertCartGame(gameCart: GameCart) {
        viewModelScope.launch(Dispatchers.IO) {
            gameDetailUsecase.insertGameCart(gameCart)
        }
    }

    fun deleteFavoriteGame(idGame: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameDetailUsecase.deleteGameFavorite(idGame)
        }
    }
}