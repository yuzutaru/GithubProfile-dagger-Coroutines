package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import android.os.Bundle
import androidx.lifecycle.*
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import kotlinx.coroutines.Dispatchers

class ProfileViewModel: ViewModel() {
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private lateinit var profileDBRepository: ProfileDBRepository

    var profileData = MutableLiveData<Profile>()
    fun profileLiveData(): LiveData<Profile> = profileData

    var isFavorited = MutableLiveData<String>()

    var saveFavorite = MutableLiveData<String>()

    init {
        val appComponent = GithubApp.instance?.getAppComponent()
        if (appComponent != null) {
            profileDBRepository = appComponent.profileDBRepository()
        }
    }

    fun getArgs(args: Bundle?) {
        profileData.value = args?.get("ARGUMENT_PROFILE") as Profile
    }

    fun isFavoritedLiveData() = isFavorited.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(profileDBRepository.getProfile(it))
        }
    }

    var favoriteProfile = saveFavorite.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(profileData.value?.let {
                profileDBRepository.insert(it)
            })
        }
    }
}