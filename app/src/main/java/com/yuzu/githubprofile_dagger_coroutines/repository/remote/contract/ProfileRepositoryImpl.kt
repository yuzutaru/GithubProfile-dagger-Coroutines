package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Response
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    override suspend fun popularUserList(q: String, perPage: Int, sort: String, order: String): Response<List<UserData>> {
        return api.popularUserList(q, perPage, sort, order)
    }

    override suspend fun userDetail(username: String): Response<ProfileData> {
        return api.userDetail(username)
    }
}