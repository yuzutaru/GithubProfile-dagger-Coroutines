package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserPagingSource
import kotlinx.coroutines.Dispatchers

class PopularViewModel: ViewModel() {
    lateinit var profileRepository: ProfileRepository
    val user = MutableLiveData<String>()
    val profile = MutableLiveData<String>()

    init {
        val appComponent = GithubApp.instance?.getAppComponent()
        if (appComponent != null) {
            profileRepository = appComponent.profileRepository()
        }
    }

    fun getUser(input: String) {
        user.value = input
    }

    var userLiveData = user.switchMap {
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { UserPagingSource(profileRepository, it) }
        ).liveData
    }

    var profileLiveData = profile.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(profileRepository.userDetail(it))
        }
    }
}