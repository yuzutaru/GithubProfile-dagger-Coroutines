package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Response
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

interface ProfileRepository {
    suspend fun userList(since: Int): Response<List<UserData>>
    suspend fun userDetail(username: String): Response<ProfileData>
}