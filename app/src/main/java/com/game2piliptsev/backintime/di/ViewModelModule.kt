package com.game2piliptsev.backintime.di

import androidx.lifecycle.ViewModel
import com.game2piliptsev.backintime.presentation.viewmodels.GameViewModel
import com.game2piliptsev.backintime.presentation.viewmodels.ResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun bindGameViewModel(viewModel: GameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    fun bindResultViewModel(viewModel: ResultViewModel): ViewModel
}