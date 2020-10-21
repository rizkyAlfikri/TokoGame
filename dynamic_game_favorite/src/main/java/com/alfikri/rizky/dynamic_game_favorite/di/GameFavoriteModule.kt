package com.alfikri.rizky.dynamic_game_favorite.di

import com.alfikri.rizky.domain.interactor.GameFavoriteInteractor
import com.alfikri.rizky.domain.usecase.GameFavoriteUseCae
import com.alfikri.rizky.dynamic_game_favorite.adapter.GameFavoriteAdapter
import com.alfikri.rizky.dynamic_game_favorite.favorite.FavoriteGameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version FavoriteModule, v 0.1 10/13/2020 9:28 PM by Rizky Alfikri Rachmat
 */

val usecaseFavoriteModule = module {
    factory <GameFavoriteUseCae>{ GameFavoriteInteractor(get()) }
}

val adapterFavoriteModule = module {
    factory { GameFavoriteAdapter() }
}

val viewmodelFavoriteModule = module {
    viewModel { FavoriteGameViewModel(get()) }
}