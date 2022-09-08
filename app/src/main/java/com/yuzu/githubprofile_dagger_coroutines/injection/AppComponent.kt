package com.yuzu.githubprofile_dagger_coroutines.injection

import android.app.Application
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)

    //Profile API
    fun profileApi(): ProfileApi
    fun profileRepository(): ProfileRepository
}