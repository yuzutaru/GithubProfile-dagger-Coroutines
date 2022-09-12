package com.yuzu.githubprofile_dagger_coroutines.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile

/**
 * Created by Yustar Pramudana on 12/09/2022
 */

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ProfileDB: RoomDatabase() {
    abstract fun profileDAO(): ProfileDAO
}