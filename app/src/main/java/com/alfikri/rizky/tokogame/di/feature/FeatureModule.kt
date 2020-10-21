package com.alfikri.rizky.tokogame.di.feature

import com.alfikri.rizky.domain.interactor.GameDetailInteractor
import com.alfikri.rizky.domain.interactor.GameInteractor
import com.alfikri.rizky.domain.interactor.GameMainInteractor
import com.alfikri.rizky.domain.usecase.GameDetailUsecase
import com.alfikri.rizky.domain.usecase.GameMainUseCase
import com.alfikri.rizky.domain.usecase.GameUsecase
import com.alfikri.rizky.tokogame.MainViewModel
import com.alfikri.rizky.tokogame.adapter.GameListAdapter
import com.alfikri.rizky.tokogame.adapter.GamePromoAdapter
import com.alfikri.rizky.tokogame.adapter.PlatformAdapter
import com.alfikri.rizky.tokogame.feature.detail.DetailGameViewModel
import com.alfikri.rizky.tokogame.feature.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version FeatureModule, v 0.1 9/23/2020 2:09 PM by Rizky Alfikri Rachmat
 */
val usecaseModule = module {
    factory<GameUsecase> { GameInteractor(get()) }
    factory<GameDetailUsecase> { GameDetailInteractor(get()) }
    factory <GameMainUseCase>{ GameMainInteractor(get()) }
}

val adapterModule = module {
    factory {
        GameListAdapter(get())
    }
    factory {
        GamePromoAdapter(get())
    }
    factory {
        PlatformAdapter()
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}