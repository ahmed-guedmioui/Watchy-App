package com.ahmed_apps.watchy_course.main.di

import android.app.Application
import androidx.room.Room
import com.ahmed_apps.watchy_course.main.data.local.MediaDatabase
import com.ahmed_apps.watchy_course.main.data.remote.api.MediaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * @author Ahmed Guedmioui
 */
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesMediaApi() : MediaApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MediaApi.BASE_URL)
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesMediaDatabase(application: Application): MediaDatabase {
        return Room.databaseBuilder(
            application,
            MediaDatabase::class.java,
            "media-db.db"
        ).build()
    }


}

















