package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.*
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

open class ProfileRepositoryImpl(private val api: ProfileApi, private val responseHandler: ResponseHandler): ProfileRepository {
    override suspend fun popularUserList(q: String, type: String, page: Int, perPage: Int,
                                         sort: String, order: String): Resource<SearchUser> {
        return try {
            val response = api.popularUserList(q, type, page, perPage, sort, order)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun userDetail(username: String): Resource<Profile> {
        return try {
            val response = api.userDetail(username)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}