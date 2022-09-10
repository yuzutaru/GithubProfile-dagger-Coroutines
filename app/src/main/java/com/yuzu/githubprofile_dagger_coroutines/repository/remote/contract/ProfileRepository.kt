package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

interface ProfileRepository {
    suspend fun popularUserList(q: String, page: Int, perPage: Int, sort: String, order: String): Resource<List<User>>
    suspend fun userDetail(username: String): Resource<Profile>
}