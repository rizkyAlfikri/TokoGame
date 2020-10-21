package com.alfikri.rizky.tokogame.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.Game
import com.alfikri.rizky.domain.usecase.GameUsecase
import com.alfikri.rizky.tokogame.utils.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val gameUsecase: GameUsecase) : ViewModel() {

    private val promoQuery = mapOf(
        GameQueryTarget.ORDERING to GameSortingTarget.RELEASED,
        GameQueryTarget.DATES to "${getCurrentDateTime().formatToEarlyYear()},${getCurrentDateTime().format()}"
    )

    private val storeQuery = mutableMapOf(
        GameQueryTarget.ORDERING to GameSortingTarget.ADDED,
        GameQueryTarget.DATES to "${getCurrentDateTime().formatToEarlyYear()},${getCurrentDateTime().format()}"
    )

    private val _gameModelPromo = MutableLiveData<Resource<List<Game>>>()

    private val _gameModelStores = MutableLiveData<Resource<List<Game>>>()

    init {
        viewModelScope.launch {
            gameUsecase.getGamePromo(promoQuery).collect { source ->
                _gameModelPromo.value = source
            }

            gameUsecase.getGameStore(storeQuery).collect { source ->
                _gameModelStores.value = source
            }
        }
    }

    val gameStores: LiveData<Resource<List<Game>>>
        get() = _gameModelStores

    val gamePromo: LiveData<Resource<List<Game>>>
        get() = _gameModelPromo

    fun updateGameStore(sortedBy: String, page: Int) {
        storeQuery[GameQueryTarget.ORDERING] = sortedBy
        storeQuery[GameQueryTarget.PAGE] = page.toString()
        viewModelScope.launch {
            gameUsecase.getGameStore(storeQuery).collect { source ->
                _gameModelStores.value = source
            }
        }
    }

    fun updateGameStoreFromPage(page: Int) {
        storeQuery[GameQueryTarget.PAGE] = page.toString()
        viewModelScope.launch {
            gameUsecase.getGameStore(storeQuery).collect { source ->
                _gameModelStores.value = source
            }
        }
    }
}