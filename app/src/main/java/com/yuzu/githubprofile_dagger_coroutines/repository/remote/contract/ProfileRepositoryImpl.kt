package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Response
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    override suspend fun userList(since: Int): Response<List<UserData>> {
        return api.userList(since)
    }

    override suspend fun userDetail(username: String): Response<ProfileData> {
        return api.userDetail(username)
    }
}