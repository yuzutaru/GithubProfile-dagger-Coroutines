package com.yuzu.githubprofile_dagger_coroutines.repository.remote.api

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User

class FakeProfileApi: ProfileApi {
    private val userList = mutableListOf<User>()

    fun addUser(user: User) {
        userList.add(user)
    }

    override suspend fun popularUserList(
        q: String,
        type: String,
        page: Int,
        perPage: Int,
        sort: String,
        order: String
    ): List<User> {
        return userList
    }

    override suspend fun userDetail(username: String): Profile {
        TODO("Not yet implemented")
    }
}