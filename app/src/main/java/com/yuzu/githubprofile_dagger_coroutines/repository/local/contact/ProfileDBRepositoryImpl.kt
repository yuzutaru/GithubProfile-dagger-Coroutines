package com.yuzu.githubprofile_dagger_coroutines.repository.local.contact

import androidx.lifecycle.LiveData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDAO

/**
 * Created by Yustar Pramudana on 12/09/2022
 */

class ProfileDBRepositoryImpl(private val dao: ProfileDAO): ProfileDBRepository {
    override suspend fun getAllProfiles(): List<Profile> {
        return dao.getAllProfiles()
    }

    override suspend fun getProfile(login: String): Profile {
        return dao.getProfile(login)
    }

    override suspend fun insert(profileData: Profile) {
        dao.insert(profileData)
    }

    override suspend fun insert(profileDataList: List<Profile>) {
        dao.insert(profileDataList)
    }
}