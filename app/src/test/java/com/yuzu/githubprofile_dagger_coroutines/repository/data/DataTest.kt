package com.yuzu.githubprofile_dagger_coroutines.repository.data

import org.junit.Assert.assertEquals
import kotlin.test.Test

class DataTest {
    @Test
    fun `Profile Test`() {
        val profile = Profile(0)
        assertEquals(profile.id, 0)
    }

    @Test
    fun `Resource Test`() {
        val resource = Resource(Status.SUCCESS, User(0,0), "success")
        assertEquals(resource.status, Status.SUCCESS)
    }

    @Test
    fun `ResponseHandler handleSuccess Test`() {
        val responseHandler = ResponseHandler()
        val resource = responseHandler.handleSuccess(User(0,0))
        assertEquals(resource, Resource(Status.SUCCESS, User(0,0), null))
    }

    @Test
    fun `SearchUser Test`() {
        val searchUser = SearchUser(items = listOf(User(0,0)))
        assertEquals(searchUser.items, listOf(User(0,0)))
    }

    @Test
    fun `User Test`() {
        val user = User(0, 0)
        assertEquals(user.id, 0)
    }
}