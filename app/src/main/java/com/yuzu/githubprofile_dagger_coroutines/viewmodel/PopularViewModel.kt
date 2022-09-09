package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository

class PopularViewModel: ViewModel() {
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val profileRepository: ProfileRepository

    init {
        val appComponent = GithubApp.instance.getAppComponent()
        profileRepository = appComponent.profileRepository()
    }
}