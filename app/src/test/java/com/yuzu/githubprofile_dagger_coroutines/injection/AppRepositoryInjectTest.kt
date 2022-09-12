package com.yuzu.githubprofile_dagger_coroutines.injection

import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.SearchUser
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by Yustar Pramud54ana on 25/02/2021
 */

class AppRepositoryInjectTest {
    @Inject
    lateinit var api: ProfileApi

    @Inject
    lateinit var profileRepo: ProfileRepository

    @Before
    fun setUp() {
        val component = DaggerTestApplicationComponent.builder()
            .appModule(TestApplicationModule(GithubApp()))
            .build()
        component.into(this)
    }

    @Test
    fun apiUserListTest() {
        runBlocking {
            Assert.assertNotNull(api)
            coEvery { api.popularUserList("followers:>1000", "Users",1,10,
                "followers", "desc") } returns SearchUser(items = listOf(User(0, 0)))
            val result = api.popularUserList("followers:>1000", "Users",1,10,
                "followers", "desc")
            assertEquals(SearchUser(items = listOf(User(0, 0))), api.popularUserList("followers:>1000", "Users",1,10,
                "followers", "desc"))
        }
    }

    @Test
    fun apiUserDetailTest() {
        runBlocking {
            Assert.assertNotNull(api)
            coEvery { api.userDetail("name") } returns Profile(0)
            val result = api.userDetail("name")
            assertEquals(Profile(0), api.userDetail("name"))
        }
    }
}