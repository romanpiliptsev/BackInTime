package com.game2piliptsev.backintime.di

import com.game2piliptsev.backintime.data.RepositoryImpl
import com.game2piliptsev.backintime.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface Module {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object {
        @Provides
        @ApplicationScope
        fun provideRepositoryImpl(): RepositoryImpl {
            return RepositoryImpl
        }
    }
}