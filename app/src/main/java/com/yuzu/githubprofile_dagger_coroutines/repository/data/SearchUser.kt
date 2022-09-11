package com.yuzu.githubprofile_dagger_coroutines.repository.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUser(
    @SerializedName("total_count")
    val totalCount: Long? = null,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean? = null,
    val items: List<User>? = null
): Parcelable