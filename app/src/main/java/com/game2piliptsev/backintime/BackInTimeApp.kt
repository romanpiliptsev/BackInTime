package com.game2piliptsev.backintime

import android.app.Application
import com.game2piliptsev.backintime.di.ApplicationComponent
import com.game2piliptsev.backintime.di.DaggerApplicationComponent

class BackInTimeApp : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .build()
    }
}