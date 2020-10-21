package com.alfikri.rizky.tokogame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.domain.usecase.GameMainUseCase
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import com.alfikri.rizky.tokogame.model.GameCartModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainViewModel, v 0.1 10/6/2020 8:15 PM by Rizky Alfikri Rachmat
 */
class MainViewModel(private val gameMainUseCase: GameMainUseCase) : ViewModel() {

    private val _gameInCart = MutableLiveData<List<GameCartModel>>()

    val gameInCart: LiveData<List<GameCartModel>>
        get() = _gameInCart

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameMainUseCase.getAllCartGame().map { GameModelMapper.transformFromCart(it) }.collect { source ->
                _gameInCart.postValue(source)
            }
        }
    }
}