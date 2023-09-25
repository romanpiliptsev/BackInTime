package com.game2piliptsev.backintime.di

import com.game2piliptsev.backintime.presentation.fragments.GameFragment
import com.game2piliptsev.backintime.presentation.fragments.ResultFragment
import dagger.Component

@ApplicationScope
@Component(modules = [Module::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: GameFragment)
    fun inject(fragment: ResultFragment)
}