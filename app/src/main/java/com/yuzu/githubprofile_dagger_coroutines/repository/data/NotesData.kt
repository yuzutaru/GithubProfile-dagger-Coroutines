package com.yuzu.githubprofile_dagger_coroutines.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

@Entity
data class NotesData(
        @PrimaryKey
        var id: Int,
        var login: String? = null,
        var notes: String? = null
)
