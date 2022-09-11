package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import androidx.paging.PagingSource
import com.yuzu.githubprofile_dagger_coroutines.repository.data.ResponseHandler
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.repository.data.UserFactory
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.FakeProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.FakeProfileRepoImpl
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.junit.JUnitAsserter.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UserPagingSourceTest {
    private val responseHandler = ResponseHandler()
    private lateinit var pagingSource: UserPagingSource

    private val userList = listOf(User(0,0), User(1, 0))

    private val factory = UserFactory()
    private val fakeUserList = listOf(
        factory.createUser(0),
        factory.createUser(0)
    )
    private val fakeApi = FakeProfileApi().apply {
        fakeUserList.forEach { user -> addUser(user) }
    }
    private lateinit var fakeRepo: ProfileRepository

    @Before
    fun setUp() {

        fakeRepo = FakeProfileRepoImpl(fakeApi, responseHandler)
        pagingSource = UserPagingSource(fakeRepo, "")
    }

    @Test
    fun pageKeyedSubredditPagingSource() = runTest {

        val pagingSource = UserPagingSource(fakeRepo, "")
        assertEquals("test",
            expected = PagingSource.LoadResult.Page(
                data = userList,
                prevKey = null,
                nextKey = 2
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
}