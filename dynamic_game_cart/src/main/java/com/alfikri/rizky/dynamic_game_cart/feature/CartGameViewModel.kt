package com.alfikri.rizky.dynamic_game_cart.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.domain.usecase.GameCartUseCase
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import com.alfikri.rizky.tokogame.model.GameCartModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartViewModel, v 0.1 10/10/2020 11:21 AM by Rizky Alfikri Rachmat
 */
class CartGameViewModel(private val gameCartUsecase: GameCartUseCase) : ViewModel() {

    private val _gameCarts = MutableLiveData<List<GameCartModel>>()

    val gameCarts: LiveData<List<GameCartModel>>
        get() = _gameCarts

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameCartUsecase.getAllCartGame()
                .map { gameCarts -> GameModelMapper.transformFromCart(gameCarts) }
                .collect { games ->
                    _gameCarts.postValue(games)
                }
        }
    }

    fun updateCheckedGame(gameCartModel: GameCartModel) {
        viewModelScope.launch(Dispatchers.IO){
            val gameCart = GameModelMapper.transformFromCartModel(gameCartModel)
            gameCartUsecase.updateCheckedGameCart(gameCart)
        }
    }

    fun insertCartGame(gameCartModel: GameCartModel) {
        viewModelScope.launch(Dispatchers.IO){
            val gameCart = GameModelMapper.transformFromCartModel(gameCartModel)
            gameCartUsecase.insertGameCart(gameCart)
        }
    }

    fun deleteCartGame(idGame: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameCartUsecase.deleteGameCart(idGame)
        }
    }
}