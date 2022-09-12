package com.yuzu.githubprofile_dagger_coroutines.repository.local.contact

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile


/**
 * Created by Yustar Pramudana on 12/09/2022
 */

interface ProfileDBRepository {
    suspend fun getAllProfiles(): List<Profile>
    suspend fun getProfile(login: String): Profile
    suspend fun insert(profileData: Profile)
    suspend fun insert(profileDataList: List<Profile>)
}