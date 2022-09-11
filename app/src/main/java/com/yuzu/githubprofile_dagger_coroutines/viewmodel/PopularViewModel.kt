package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserPagingSource

class PopularViewModel: ViewModel() {
    private val profileRepository: ProfileRepository
    private val user = MutableLiveData<String>()

    init {
        val appComponent = GithubApp.instance.getAppComponent()
        profileRepository = appComponent.profileRepository()
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
}