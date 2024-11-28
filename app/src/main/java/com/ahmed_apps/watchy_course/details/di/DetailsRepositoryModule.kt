package com.ahmed_apps.watchy_course.details.di

import com.ahmed_apps.watchy_course.details.data.repository.DetailsRepositoryImpl
import com.ahmed_apps.watchy_course.details.data.repository.SimilarRepositoryImpl
import com.ahmed_apps.watchy_course.details.data.repository.VideosRepositoryImpl
import com.ahmed_apps.watchy_course.details.domain.repository.DetailsRepository
import com.ahmed_apps.watchy_course.details.domain.repository.SimilarRepository
import com.ahmed_apps.watchy_course.details.domain.repository.VideosRepository
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
abstract class DetailsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDetailsRepository(
        detailsRepositoryImpl: DetailsRepositoryImpl
    ): DetailsRepository

    @Binds
    @Singleton
    abstract fun bindVideosRepository(
        videosRepositoryImpl: VideosRepositoryImpl
    ): VideosRepository

    @Binds
    @Singleton
    abstract fun bindSimilarRepository(
       similarRepositoryImpl: SimilarRepositoryImpl
    ): SimilarRepository

}
























