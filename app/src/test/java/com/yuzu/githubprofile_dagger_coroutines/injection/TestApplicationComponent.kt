package com.yuzu.githubprofile_dagger_coroutines.injection

import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDAO
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDB
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
    fun into(test: AppRepositoryInjectTest)

    //Profile API
    fun profileApi(): ProfileApi
    fun profileRepository(): ProfileRepository

    //Profile DB
    fun profileDB(): ProfileDB
    fun profileDAO(): ProfileDAO
    fun profileDBRepository(): ProfileDBRepository
}