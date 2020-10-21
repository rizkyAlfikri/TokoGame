package com.alfikri.rizky.dynamic_game_cart.di

import com.alfikri.rizky.domain.interactor.GameCartInteractor
import com.alfikri.rizky.domain.usecase.GameCartUseCase
import com.alfikri.rizky.dynamic_game_cart.adapter.GameCartAdapter
import com.alfikri.rizky.dynamic_game_cart.feature.CartGameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartModule, v 0.1 10/10/2020 12:58 PM by Rizky Alfikri Rachmat
 */

val usecaseCartModule = module {
    factory <GameCartUseCase> { GameCartInteractor(get()) }
}

val adapterCartModule = module {
    factory { GameCartAdapter() }
}

val viewModelCartModule = module {
    viewModel { CartGameViewModel(get()) }
}