package com.alfikri.rizky.dynamic_game_favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.domain.usecase.GameFavoriteUseCae
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import com.alfikri.rizky.tokogame.model.GameFavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteGameViewModel(private val favoriteGameUseCae: GameFavoriteUseCae) :
    ViewModel() {

    private val _gameFavorite = MutableLiveData<List<GameFavoriteModel>>()

    val gameInCart: LiveData<List<GameFavoriteModel>>
        get() = _gameFavorite

    init {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteGameUseCae.getAllFavoriteGames().map {
                GameModelMapper.transformFromFavorite(it) }.collect { source ->
                _gameFavorite.postValue(source)
            }
        }
    }

    fun insertFavoriteGame(gameFavoriteModel: GameFavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val gameFavorite = GameModelMapper.transformFromFavoriteModel(gameFavoriteModel)
            favoriteGameUseCae.insertGameFavorite(gameFavorite)
        }
    }

    fun deleteFavoriteGame(idGame: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteGameUseCae.deleteGameFavorite(idGame)
        }
    }
}
