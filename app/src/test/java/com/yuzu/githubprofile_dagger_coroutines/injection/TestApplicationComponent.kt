package com.yuzu.githubprofile_dagger_coroutines.injection

import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.PopularViewModelTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yustar Pramudana on 11/09/2022
 */

@Singleton
@Component(modules = [AppModule::class])
interface TestApplicationComponent {
    fun into(test: PopularViewModelTest)
    //Profile API
    fun profileApi(): ProfileApi
    fun profileRepository(): ProfileRepository
}