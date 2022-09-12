package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.*
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel: ViewModel() {
    lateinit var profileDBRepository: ProfileDBRepository
    var profile = MutableLiveData("profile")

    init {
        val appComponent = GithubApp.instance?.getAppComponent()
        if (appComponent != null) {
            profileDBRepository = appComponent.profileDBRepository()
        }
    }

    var profileLiveData = profile.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(profileDBRepository.getAllProfiles())
        }
    }
}