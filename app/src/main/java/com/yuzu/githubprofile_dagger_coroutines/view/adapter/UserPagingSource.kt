package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import retrofit2.HttpException
import java.io.IOException

private const val USER_STARTING_PAGE_INDEX = 1

class UserPagingSource(private val repository: ProfileRepository, private val search: String): PagingSource<Int, User>() {
    private var BASE_QUERY = "followers:>1000"

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: USER_STARTING_PAGE_INDEX
        return try {
            val response = repository.popularUserList(q = "$BASE_QUERY $search", type = "Users",
                page = pageIndex, perPage = 10, sort = "followers", order = "desc")

            val users = response.data?.items

            var nextKey: Int? = null

            if (users != null && users.isNotEmpty()) {
                nextKey = pageIndex + 1
            }

            LoadResult.Page(
                data = users!!,
                prevKey = if (pageIndex == USER_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: NullPointerException) {
            return LoadResult.Error(exception)
        }
    }
}