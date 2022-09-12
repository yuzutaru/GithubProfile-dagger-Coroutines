package com.yuzu.githubprofile_dagger_coroutines.repository.local.contact

import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Resource


/**
 * Created by Yustar Pramudana on 12/09/2022
 */

interface ProfileDBRepository {
    suspend fun getAllProfiles(): Resource<List<Profile>>
    suspend fun getProfile(login: String): Resource<Profile>
    suspend fun insert(profileData: Profile): Resource<Long>
    suspend fun insert(profileDataList: List<Profile>): Resource<List<Long>>
}