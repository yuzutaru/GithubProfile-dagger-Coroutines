package com.yuzu.githubprofile_dagger_coroutines.repository.local.contact

import androidx.lifecycle.LiveData
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile


/**
 * Created by Yustar Pramudana on 12/09/2022
 */

interface ProfileDBRepository {
    fun getAllProfiles(): LiveData<List<Profile>>
    fun getProfile(login: String): LiveData<Profile>
    suspend fun insert(profileData: Profile)
    suspend fun insert(profileDataList: List<Profile>)
}