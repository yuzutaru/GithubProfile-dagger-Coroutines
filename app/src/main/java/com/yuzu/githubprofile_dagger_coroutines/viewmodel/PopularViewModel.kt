package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.NETWORK_PAGE_SIZE
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class PopularViewModel: ViewModel() {
    private val profileRepository: ProfileRepository
    private val user = MutableLiveData<String>()

    init {
        val appComponent = GithubApp.instance.getAppComponent()
        profileRepository = appComponent.profileRepository()
    }

    fun getUser() {
        user.value = "start"
    }

    var userLiveData = user.switchMap {
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { UserPagingSource(profileRepository) }
        ).liveData
    }
}