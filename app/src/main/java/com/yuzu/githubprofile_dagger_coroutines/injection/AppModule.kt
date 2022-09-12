package com.yuzu.githubprofile_dagger_coroutines.injection

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.yuzu.githubprofile_dagger_coroutines.repository.data.ResponseHandler
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepository
import com.yuzu.githubprofile_dagger_coroutines.repository.local.contact.ProfileDBRepositoryImpl
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDAO
import com.yuzu.githubprofile_dagger_coroutines.repository.local.db.ProfileDB
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepositoryImpl
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.api.ProfileApi
import com.yuzu.githubprofile_dagger_coroutines.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile_dagger_coroutines.utils.BASE_URL
import com.yuzu.githubprofile_dagger_coroutines.utils.TIMEOUT_HTTP
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Yustar Pramudana on 08/09/2022
 */

@Module
open class AppModule(private val app: Application) {
    @Provides
    fun app(): Application {
        return app
    }

    private fun provideOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains

            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            var builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })

            var loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            return builder.addInterceptor(loggingInterceptor)
                .readTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .build()

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    //Profile API
    @Provides
    @Singleton
    open fun profileApi(): ProfileApi {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    open fun profileRepository(api: ProfileApi, handler: ResponseHandler): ProfileRepository {
        return ProfileRepositoryImpl(api, handler)
    }

    @Provides
    @Singleton
    open fun responseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    //Profile DB
    @Provides
    @Singleton
    open fun profileDB(): ProfileDB {
        return Room.databaseBuilder(app, ProfileDB::class.java, "profile.db").build()
    }

    @Provides
    @Singleton
    open fun profileDAO(db: ProfileDB): ProfileDAO {
        return db.profileDAO()
    }

    @Provides
    @Singleton
    open fun profileDBRepository(dao: ProfileDAO, responseHandler: ResponseHandler): ProfileDBRepository {
        return ProfileDBRepositoryImpl(dao, responseHandler)
    }
}