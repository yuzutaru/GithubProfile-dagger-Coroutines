package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel: ViewModel() {
    lateinit var profileDBRepository: ProfileDBRepository
    var profileLiveData: LiveData<List<Profile>>

    init {
        val appComponent = GithubApp.instance?.getAppComponent()
        if (appComponent != null) {
            profileDBRepository = appComponent.profileDBRepository()
        }

        profileLiveData = liveData(Dispatchers.IO) {
            emit(profileDBRepository.getAllProfiles())
        }
    }
}