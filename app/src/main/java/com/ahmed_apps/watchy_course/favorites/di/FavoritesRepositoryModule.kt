package com.ahmed_apps.watchy_course.favorites.di

import com.ahmed_apps.watchy_course.favorites.data.repository.BackendFavoritesRepositoryImpl
import com.ahmed_apps.watchy_course.favorites.data.repository.FavoritesRepositoryImpl
import com.ahmed_apps.watchy_course.favorites.domain.repository.BackendFavoritesRepository
import com.ahmed_apps.watchy_course.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Ahmed Guedmioui
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository

    @Binds
    @Singleton
    abstract fun bindBackendFavoritesRepository(
       backendFavoritesRepositoryImpl: BackendFavoritesRepositoryImpl
    ): BackendFavoritesRepository

}






















