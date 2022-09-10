package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 10

internal class UserRemoteDataSourceImpl(private val profileRepository: ProfileRepository):
    UserRemoteDataSource {

    override suspend fun getPopularUser(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserPagingSource(repository = profileRepository)
            }
        ).flow
    }
}