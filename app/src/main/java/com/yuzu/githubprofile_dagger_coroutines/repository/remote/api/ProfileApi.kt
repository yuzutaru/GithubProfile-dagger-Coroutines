package com.yuzu.githubprofile_dagger_coroutines.repository.remote.api

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Response
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData
import retrofit2.http.*

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

interface ProfileApi {

    /**
     * User List
     * */
    @GET(value = "users")
    suspend fun userList(@Query("since") since: Int): Response<List<UserData>>

    /**
     * User Detail
     * */
    @GET(value = "users/{username}")
    suspend fun userDetail(@Path(value = "username") username: String): Response<ProfileData>
}