package com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.ProfileData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.ResponseHandler
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserData
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

class ProfileRepositoryImpl(private val api: ProfileApi, private val responseHandler: ResponseHandler): ProfileRepository {
    override suspend fun popularUserList(q: String, perPage: Int, sort: String, order: String): Resource<List<UserData>> {
        return try {
            val response = api.popularUserList(q, perPage, sort, order)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun userDetail(username: String): Resource<ProfileData> {
        return try {
            val response = api.userDetail(username)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}