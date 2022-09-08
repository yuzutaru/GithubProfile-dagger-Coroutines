package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Response
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData
import retrofit2.http.Query

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

interface ProfileRepository {
    suspend fun popularUserList(q: String, perPage: Int, sort: String, order: String): Response<List<UserData>>
    suspend fun userDetail(username: String): Response<ProfileData>
}