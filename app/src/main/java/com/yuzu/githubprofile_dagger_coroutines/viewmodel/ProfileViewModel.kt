package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile

class ProfileViewModel: ViewModel() {
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    var profileData = MutableLiveData<Profile>()

    fun getArgs(args: Bundle?) {
        profileData.value = args?.get("ARGUMENT_PROFILE") as Profile
    }
}