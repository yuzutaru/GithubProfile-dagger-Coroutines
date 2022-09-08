package com.yuzu.githubprofile_dagger_coroutines

import android.app.Application
import android.content.Context
import com.yuzu.githubprofile_dagger_coroutines.injection.AppComponent
import com.yuzu.githubprofile_dagger_coroutines.injection.AppModule
import com.yuzu.githubprofile_dagger_coroutines.injection.DaggerAppComponent

class GithubApp: Application() {
    lateinit var component: AppComponent

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: GithubApp private set
    }

    operator fun get(context: Context): GithubApp {
        return context.applicationContext as GithubApp
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        //MultiDex.install(this)
    }

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        instance = this
        // DI
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)
        //initTextSize()
    }
}