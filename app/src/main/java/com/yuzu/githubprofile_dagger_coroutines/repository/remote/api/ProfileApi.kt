package com.yuzu.githubprofile_dagger_coroutines.repository.remote.api

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import retrofit2.http.*

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

interface ProfileApi {

    /**
     * Popular User List
     * curl https://api.github.com/search/users\?q\=followers:\>1000\&page\=1\&per_page\=10\&sort\=followers\&order\=desc
     * */
    @GET(value = "users")
    suspend fun popularUserList(@Query("q") q: String,
                                @Query("type") type: String,
                                @Query("page") page: Int,
                                @Query("per_page") perPage: Int,
                                @Query("sort") sort: String,
                                @Query("order") order: String): List<User>

    /**
     * User Detail
     * */
    @GET(value = "users/{username}")
    suspend fun userDetail(@Path(value = "username") username: String): Profile
}