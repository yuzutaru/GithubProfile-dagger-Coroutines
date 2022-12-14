package com.yuzu.githubprofile_dagger_coroutines.repository.local.contact

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.ResponseHandler
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDAO

/**
 * Created by Yustar Pramudana on 12/09/2022
 */

class ProfileDBRepositoryImpl(private val dao: ProfileDAO, private val responseHandler: ResponseHandler): ProfileDBRepository {
    override suspend fun getAllProfiles(): Resource<List<Profile>> {
        return try {
            val response = dao.getAllProfiles()
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getProfile(login: String): Resource<Profile> {
        return try {
            val response = dao.getProfile(login)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun insert(profileData: Profile): Resource<Long> {
        return try {
            val response = dao.insert(profileData)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun insert(profileDataList: List<Profile>): Resource<List<Long>> {
        return try {
            val response = dao.insert(profileDataList)
            return responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}