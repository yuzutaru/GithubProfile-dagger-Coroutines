package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import androidx.paging.PagingData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getPopularUser(): Flow<PagingData<User>>
}
