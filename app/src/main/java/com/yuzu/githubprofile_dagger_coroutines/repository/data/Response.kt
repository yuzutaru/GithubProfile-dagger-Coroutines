package com.yuzu.githubprofile_dagger_coroutines.repository.data

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

class Response<T>(
    val status: Status,
    val data: T?,
    val error: Throwable?
) {
    companion object {
        fun <T> empty() = Response<T>(Status.EMPTY, null, null)
        fun <T> succeed(data: T) = Response(Status.SUCCEED, data, null)
        fun <T> error(t: Throwable) = Response<T>(Status.FAILED, null, t)
        fun <T> networkLost() = Response<T>(Status.NO_CONNECTION, null, null)
    }
}

enum class Status {
    EMPTY,
    SUCCEED,
    FAILED,
    NO_CONNECTION
}

object NoNetworkException : Exception()