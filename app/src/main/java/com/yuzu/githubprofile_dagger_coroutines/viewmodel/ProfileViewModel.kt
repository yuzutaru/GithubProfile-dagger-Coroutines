package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile

class ProfileViewModel: ViewModel() {
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    var profileData = MutableLiveData<Profile>()
}