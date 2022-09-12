package com.yuzu.githubprofile_dagger_coroutines.repository.local

import com.yuzu.githubprofile_dagger_coroutines.repository.data.*
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepositoryImpl
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDAO
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class ProfileDBRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var dao: ProfileDAO
    private lateinit var repository: ProfileDBRepository

    private val profileData = Profile(0, "yuzu")
    private val profileList = listOf(Profile(0,"yuzu"), Profile(1, "taru"))

    private val profileDataResponse = Resource.success(profileData)
    private val profileListResponse = Resource.success(profileList)

    private val error = Resource.error("Unauthorised", null)


    @Before
    fun setUp() {
        dao = mockk()

        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            coEvery { dao.getProfile("yuzu") } returns profileData
            coEvery { dao.getAllProfiles() } returns profileList
            coEvery { dao.getProfile("Naruto") } throws mockException
        }

        repository = ProfileDBRepositoryImpl(dao, responseHandler)
    }

    @Test
    fun `test userDetail when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            Assert.assertEquals(profileDataResponse, repository.getProfile("yuzu"))
        }

    @Test
    fun `test userDetail when invalid username is requested, then error is returned`() =
        runBlocking {
            Assert.assertEquals(error, repository.getProfile("Naruto"))
        }

    @Test
    fun `test userList when valid since is requested, then userList is returned`() =
        runBlocking {
            Assert.assertEquals(profileListResponse, repository.getAllProfiles())
        }
}