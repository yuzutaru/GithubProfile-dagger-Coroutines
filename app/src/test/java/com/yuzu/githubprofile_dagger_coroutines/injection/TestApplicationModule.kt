package com.yuzu.githubprofile_dagger_coroutines.injection

import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.ResponseHandler
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import io.mockk.mockk

/**
 * Created by Yustar Pramudana on 11/09/2022
 */

class TestApplicationModule(application: GithubApp): AppModule(application) {
    override fun profileApi(): ProfileApi = mockk()
    override fun profileRepository(api: ProfileApi, handler: ResponseHandler): ProfileRepository = mockk()
    override fun responseHandler(): ResponseHandler = mockk()
}