package com.yuzu.githubprofile_dagger_coroutines.repository.data

import java.util.concurrent.atomic.AtomicInteger

class UserFactory {
    private val counter = AtomicInteger(0)
    fun createUser(since: Int) : User {
        val id = counter.getAndIncrement()
        return User(id, since)
    }
}