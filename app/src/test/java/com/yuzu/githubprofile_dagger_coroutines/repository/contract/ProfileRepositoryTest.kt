package com.yuzu.githubprofile_dagger_coroutines.repository.contract

import com.yuzu.githubprofile_dagger_coroutines.repository.data.*
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepositoryImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException

/**
 * Created by Yustar Pramudana on 07/07/22.
 */

@RunWith(JUnit4::class)
class ProfileRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var api: ProfileApi
    private lateinit var repository: ProfileRepository

    private val profileData = Profile(0, "yuzu")
    private val userList = listOf(User(0,0))

    private val profileDataResponse = Resource.success(profileData)
    private val userListResponse = Resource.success(userList)

    private val error = Resource.error("Unauthorised", null)


    @Before
    fun setUp() {
        api = mockk()
        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            coEvery { api.userDetail("yuzu") } returns profileData
            coEvery { api.popularUserList("followers:>1000", "Users",1,10, "followers", "desc") } returns userList
            coEvery { api.userDetail("Naruto") } throws mockException
        }

        repository = ProfileRepositoryImpl(api, responseHandler)
    }

    @Test
    fun `test userDetail when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            assertEquals(profileDataResponse, repository.userDetail("yuzu"))
        }

    @Test
    fun `test userDetail when invalid username is requested, then error is returned`() =
        runBlocking {
            assertEquals(error, repository.userDetail("Naruto"))
        }

    @Test
    fun `test userList when valid since is requested, then userList is returned`() =
        runBlocking {
            assertEquals(userListResponse, repository.popularUserList("followers:>1000", "Users",1,10, "followers", "desc"))
        }
}