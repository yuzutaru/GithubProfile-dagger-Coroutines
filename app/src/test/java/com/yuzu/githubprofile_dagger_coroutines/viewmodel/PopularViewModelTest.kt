package com.yuzu.githubprofile_dagger_coroutines.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yuzu.githubprofile_dagger_coroutines.GithubApp
import com.yuzu.githubprofile_dagger_coroutines.injection.DaggerTestApplicationComponent
import com.yuzu.githubprofile_dagger_coroutines.injection.TestApplicationModule
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.utils.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.junit.JUnitAsserter.assertEquals

class PopularViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    lateinit var viewModel: PopularViewModel

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        val component = DaggerTestApplicationComponent.builder()
            .appModule(TestApplicationModule(GithubApp()))
            .build()
        component.into(this)

        viewModel = PopularViewModel()
        viewModel.profileRepository = component.profileRepository()
    }

    @Test
    fun `getUser fill User MutableLiveData`() {
        viewModel.getUser("foo")

        // Pass:
        assertEquals("test", viewModel.user.getOrAwaitValue(), "foo")
    }
}