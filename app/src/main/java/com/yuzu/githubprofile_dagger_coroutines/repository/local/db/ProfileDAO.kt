package com.yuzu.githubprofile_dagger_coroutines.repository.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile

/**
 * Created by Yustar Pramudana on 12/09/2022
 */

@Dao
interface ProfileDAO {
    @Query("SELECT * from Profile")
    fun getAllProfiles(): LiveData<List<Profile>>

    @Query("SELECT * FROM Profile WHERE login = :login")
    fun getProfile(login: String): LiveData<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileData: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileDataList: List<Profile>)
}