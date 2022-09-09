package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

interface ProfileRepository {
    suspend fun popularUserList(q: String, perPage: Int, sort: String, order: String): Resource<List<UserData>>
    suspend fun userDetail(username: String): Resource<ProfileData>
}